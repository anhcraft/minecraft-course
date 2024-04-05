# Minecraft course
Tài nguyên khoá học Minecraft plugin.<br>
_(Materials for **Vietnamese** Minecraft plugin development course)_

## Thông tin
- Kênh thảo luận: https://minecraftvn.net/
- Liên hệ mình: Discord `@anhcraft`
- Ủng hộ tác giả: 
  - Paypal: https://paypal.me/lycheene
  - MoMo/Bank: Liên hệ qua Discord

## Mục tiêu
- Hiểu được cách server Minecraft hoạt động
- Tạo plugin cho server Minecraft từ cơ bản đến nâng cao
- Tích hợp plugin và cơ sở dữ liệu (database)

## Sản phẩm sau khoá học
- Dự án mini 1: Plugin bảo mật server
- Dự án mini 2: Plugin sửa item
- Dự án Mini 3: Plugin invsee
- Dự án Mini 4: Plugin boss
- Dự án Mini 5: Plugin hologram
- Dự án Mini 6: Plugin custom item
- Dự án Mini 7: Plugin cá cược chẵn lẻ
- Dự án lớn: Plugin minigame

## Nội dung
1. Giới thiệu về phát triển plugin Minecraft
    1. Giới thiệu về khoá học
    2. Cách thảo luận, báo lỗi, nhờ trợ giúp
    3. Ứng dụng của plugin trong server
    4. Yêu cầu khoá học
    5. Mục tiêu và lộ trình học
    6. Giới thiệu project khoá học
2. Kiến trúc và cách hoạt động của 1 server Minecraft
    1. Cách server Minecraft hoạt động
        1. Network layer
        2. Persistence layer
        3. Game logic layer
    2. Cách server Minecraft được mod
        1. Obfuscation mapping (Mojang, Fabric, Spigot)
        2. Patching
    3. Sự khác nhau giữa API và Implementation
    4. NMS là gì
    5. So sánh CraftBukkit/Spigot/Paper/Bungeecord/Velocity/Fabric/Forge
3. Tạo plugin đầu tiên
    1. Cài đặt môi trường, công cụ qua
        1. IntelliJ + Minecraft Development plugin + Maven
       2. VSCode + Maven
    2. Giới thiệu về plugin Minecraft development
    3. Tạo plugin in ra console `Hello World` dùng built-in logger
    4. Cách biên dịch, đóng gói thành file
    5. Hot reloading bằng Jetbrains runtime
4. Sơ lược về Bukkit API, Spigot API, Paper API
    1. Giới thiệu về Javadoc
    2. Cách tra cứu, tham khảo khi có vấn đề
5. Kiến trúc plugin
    1. Vòng đời plugin (Lifecycle)
    2. Plugin loading mechanism
    3. Plugin.yml
    4. Paper's new plugin.yml
    5. Cách tổ chức code trong plugin
6. Event system
    1. Cách event system hoạt động
    2. Cách listen event
    3. Cách emit event
    4. Tạo event riêng
    5. Share state giữa các event
7. Command system
    1. Tạo command, tab complete
    2. Command section trong plugin.yml
    3. Sơ lược ACF, Commands API
8. Permission system
    1. Permission section trong plugin.yml
    2. Kiểm tra permission
    3. Thêm permission
9. Configuration
    1. Dùng built-in configuration (SnakeYAML)
    2. Dùng JSON
    3. Object mapping cho configuration
10. Dự án mini 1: Plugin bảo mật server
    1. Whitelist staff cho phép
    2. 2FA dùng code
11. Chat component API
    1. Legacy chat API
    2. Bungee chat API
    3. Adventure chat API
    4. MiniMessage
12. Localization (i18n)
13. Item API
    1. Tạo item, item meta
    2. Persistent data
14. Dự án mini 2: Plugin sửa item
15. Inventory API
    1. Sửa túi đồ
    2. Tạo GUI, lưu state
    3. Multi-viewer GUI
    4. Pagination
    5. Recipes
16. Dự án Mini 3: Plugin invsee
    1. Cho phép xem inventory
    2. Cho phép sửa inventory
17. Entity API
    1. Cập nhật state entity
    2. Spawn entity
18. Dự án Mini 4: Plugin Boss
    1. Tạo boss bằng Entity API
    2. Thêm skill cho boss
19. ArmorStand
20. Block Display
21. Dự án Mini 5: Plugin Hologram
22. Block API, World API
    1. Cập nhật state block
    2. Thay đổi block
    3. Persistent data
    4. Tạo world mới
23. Scheduler
    1. Cách Bukkit scheduler hoạt động
    2. Bukkit scheduler vs Java Thread
    3. Tạo one-time và repeated task
    4. Share state giữa các scheduler
    5. Asynchronous programming
24. Particle effect
    1. Tạo đường thẳng
    2. Tạo hình tròn
    3. Tạo hình cầu
    4. Tạo hình hộp
    5. Tạo elipse
    6. Xoay hình
25. Dự án Mini 6: Plugin custom item
26. Dùng Vault API
27. Dùng Placeholder API
28. Dự án Mini 7: Plugin cá cược chẵn lẻ
29. Plugin messaging (1 chiều)
30. Kết nối Database
31. NMS
32. Dự án lớn: Plugin Minigame
