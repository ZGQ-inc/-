import json
from telethon import TelegramClient

# 填写
api_id = ''
api_hash = ''

client = TelegramClient('account', api_id, api_hash)

async def save_user_ids(group_url, output_file):
    await client.start()

    participants = await client.get_participants(group_url)
    print(f"总共 {len(participants)} 名群员")

    user_ids = [user.id for user in participants]

    with open(output_file, "w", encoding="utf-8") as f:
        json.dump(user_ids, f, indent=4)

    await client.disconnect()

import asyncio
group_url = 'https://t.me/'  # 填写
output_file = 'UID.json'
asyncio.run(save_user_ids(group_url, output_file))
