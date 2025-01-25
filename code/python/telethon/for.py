from telethon import TelegramClient, events
from telethon.tl.types import PeerChannel

# 填写
api_id = ''
api_hash = ''
source_channel = 'https://t.me/'
target_channel = 'https://t.me/'
start_id =  #min
end_id =  #max

client = TelegramClient('account', api_id, api_hash)

async def forward_messages():
    await client.start()

    source = await client.get_entity(PeerChannel(int(source_channel)) if source_channel.isdigit() else source_channel)
    target = await client.get_entity(PeerChannel(int(target_channel)) if target_channel.isdigit() else target_channel)

    async for message in client.iter_messages(source, min_id=start_id, max_id=end_id):
        try:
            if message.text or message.media:
                await client.send_message(target, message)
                print(f"{message.id} 成功")
        except Exception as e:
            print(f"{message.id} 失败: {e}")

with client:
    client.loop.run_until_complete(forward_messages())
