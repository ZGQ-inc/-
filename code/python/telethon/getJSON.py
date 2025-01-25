from telethon.sync import TelegramClient
from telethon.errors import SessionPasswordNeededError
import re
import json
from datetime import datetime

API_ID = ''  # 输入
API_HASH = ''  # 输入

def parse_url(url):
    """
    支持格式：
    https://t.me/username/
    https://t.me/c/channel_id/id
    """
    if "t.me/c/" in url:
        pattern = r"https://t\.me/c/(\d+)/(\d+)"
        match = re.match(pattern, url)
        if not match:
            raise ValueError("无效的URL")
        chat_id, message_id = map(int, match.groups())
        return chat_id, message_id, True
    else:
        pattern = r"https://t\.me/([^/]+)/(\d+)"
        match = re.match(pattern, url)
        if not match:
            raise ValueError("无效的URL")
        username_or_channel, message_id = match.groups()
        return username_or_channel, int(message_id), False

def serialize_datetime(obj):
    """
    自定义 JSON 序列化函数，将 datetime 转换为 ISO 8601 格式字符串
    """
    if isinstance(obj, datetime):
        return obj.isoformat()
    raise TypeError(f"Type {type(obj)} not serializable")

def fetch_message_json(url):
    identifier, message_id, is_private = parse_url(url)

    with TelegramClient("account", API_ID, API_HASH) as client:
        try:
            # 如果session过期或无效
            if not client.is_user_authorized():
                client.send_code_request(client.phone)
                client.sign_in(client.phone, input("请输入验证码: "))
        except SessionPasswordNeededError:
            client.sign_in(password=input("请输入两步验证密码: "))

        if is_private:
            peer = int(f"-100{identifier}")  # -100前缀
        else:
            peer = identifier

        message = client.get_messages(peer, ids=message_id)
        if not message:
            raise ValueError("未找到该消息")

        return message.to_dict()

if __name__ == "__main__":
    # 用户输入
    url = input("输入URL:")
    try:
        message_json = fetch_message_json(url)
        # 使用自定义序列化函数，格式化输出 JSON
        print(json.dumps(message_json, default=serialize_datetime, indent=4, ensure_ascii=False))
    except Exception as e:
        print(f"出错了: {e}")
