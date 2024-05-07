# Minecraft course
Tài nguyên khoá học Minecraft plugin.<br>
_(Materials for **Vietnamese** Minecraft plugin development course)_

## Mục tiêu
- Hiểu được cách server Minecraft hoạt động
- Tạo plugin cho server Minecraft từ cơ bản đến nâng cao
- Tích hợp plugin và cơ sở dữ liệu (database)

## Thông tin
- Kênh thảo luận: 
  - Forum: https://minecraftvn.net/
  - Discord: https://discord.gg/88zavUAdbQ
- Liên hệ mình: Discord `@anhcraft`
- Ủng hộ tác giả: 
  - Paypal: https://paypal.me/lycheene
  - MoMo/Bank: Liên hệ qua Discord

## Bài học
Xem tại đây: [LESSONS.md](LESSONS.md)

## Nội dung
1. Giới thiệu về phát triển plugin Minecraft
    1. Giới thiệu về khoá học
    2. Cách thảo luận, báo lỗi, nhờ trợ giúp
    3. Ứng dụng của plugin trong server
    4. Yêu cầu khoá học
    5. Mục tiêu và lộ trình học
    6. Giới thiệu project khoá học
2. Kiến trúc server Minecraft
    1. Cách server Minecraft hoạt động
        1. Giao tiếp client - server
        2. Các lớp trong cấu trúc server
        3. Game loop
    2. Cách server Minecraft được mod
        1. Obfuscation
        2. Patching
    3. So sánh các nền tảng Minecraft
    4. Sự khác nhau giữa API và Implementation
    5. NMS là gì
3. Tạo plugin đầu tiên
    1. Cài đặt môi trường và công cụ
    2. Giới thiệu về plugin Minecraft development
    3. Tạo plugin đơn giản
       1. In ra console `Hello World` dùng built-in logger
       2. Sơ lược về plugin.yml và pom.xml
    4. Cách biên dịch và đóng gói plugin
4. Kiến trúc plugin
    1. Java class loading
    2. Plugin loading
    3. Plugin.yml
    4. Tổ chức code trong plugin
    5. Sơ lược về Bukkit API, Spigot API, Paper API
5. Event system
    1. Cách event system hoạt động
    2. Cách listen event
    3. Cách emit event
    4. Tạo event riêng
    5. Share state giữa các event
6. Command system
    1. Tạo command, tab complete
    2. Command section trong plugin.yml
    3. Sơ lược ACF, Commands API
7. Configuration
    1. Dùng built-in configuration (SnakeYAML)
    2. Dùng JSON
8. Dự án mini 1: Plugin bảo mật server
    1. Whitelist staff cho phép
    2. 2FA dùng code
9. Chat component API
    1. Legacy chat API
    2. Bungee chat API
    3. Adventure chat API
    4. MiniMessage
10. Localization (i18n)
11. Named Binary Tag (NBT)
    1. Cấu trúc NBT
    2. NBT trong world, player data
    3. Xem và chỉnh sửa NBT
12. Item API
    1. Tạo item, item meta
    2. Persistent data
13. Dự án mini 2: Plugin sửa item
14. Inventory API
    1. Sửa túi đồ
    2. Tạo GUI, lưu state
    3. Multi-viewer GUI
    4. Pagination
    5. Recipes
15. Dự án Mini 3: Plugin invsee
    1. Cho phép xem inventory
    2. Cho phép sửa inventory
16. Entity API
    1. Cập nhật state entity
    2. Spawn entity
17. Dự án Mini 4: Plugin Boss
    1. Tạo boss bằng Entity API
    2. Thêm skill cho boss
18. ArmorStand
19. Display Entity
20. Dự án Mini 5: Plugin Hologram
21. Block API, World API
    1. Cập nhật state block
    2. Thay đổi block
    3. Persistent data
    4. Tạo world mới
22. Scheduler
    1. Cách Bukkit scheduler hoạt động
    2. Bukkit scheduler vs Java Thread
    3. Tạo one-time và repeated task
    4. Share state giữa các scheduler
    5. Asynchronous programming
23. Particle effect
    1. Tạo đường thẳng
    2. Tạo hình tròn
    3. Tạo hình cầu
    4. Tạo hình hộp
    5. Tạo elipse
    6. Xoay hình
24. Dự án Mini 6: Plugin custom item
25. Dùng Vault API
26. Dùng Placeholder API
    1. Parse placeholders
    2. Create custom expansion
27. Dự án Mini 7: Plugin cá cược chẵn lẻ
28. Plugin messaging (1 chiều)
    1. Forward players
29. Tích hợp Database
30. Dùng ProtocolLib
    1. Tạo Armor Stand, Display Entity bằng packet
    2. Mute sounds using packets
    3. Custom block hardness
31. Dự án lớn: Plugin Minigame (SurvivalGames)
    1. Multi-arena, Solo/Team
    2. Custom kits
    3. Custom items
    4. Random lootable chest
    5. Spectator mode
    6. Using: Vault, PlaceholderAPI, ProtocolLib, ajLeaderboards
