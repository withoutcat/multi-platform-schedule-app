# Schedule App - 多平台待办事项应用

一个使用Kotlin Multiplatform + Compose开发的待办事项和档期管理应用。

## 功能特性

- ✅ **快速创建待办**: 只需输入文本即可快速创建待办事项
- 📅 **多视图日历**: 支持日、周、月三种日历视图
- 🎨 **视觉预览**: 周视图显示每天待办数量，颜色条标识
- 👆 **手势操作**: 左滑删除，右滑编辑待办事项
- 📱 **多平台支持**: 使用KMP支持Android、iOS、桌面端

## 项目结构

```
to-do/
├── app/                 # Android应用模块
├── shared/              # 共享KMP模块
│   └── src/commonMain/kotlin/
│       ├── components/  # Compose组件
│       ├── model/       # 数据模型
│       ├── state/       # 状态管理
│       └── utils/       # 工具类
└── gradle/              # Gradle配置
```

## 核心组件

### 数据模型
- `TodoItem`: 待办事项实体类
- `Priority`: 优先级枚举
- `CalendarDay`: 日历日期数据

### 状态管理
- `AppState`: 应用全局状态管理
- `CalendarViewMode`: 日历视图模式枚举

### UI组件
- `CalendarView`: 主日历视图
- `QuickCreateDialog`: 快速创建对话框
- `SwipeableTodoCard`: 可滑动待办卡片
- `FloatingCreateButton`: 悬浮创建按钮

## 运行项目

### Android
```bash
./gradlew :app:assembleDebug
```

### 桌面端
```bash
./gradlew :shared:run
```

## 技术栈

- **Kotlin Multiplatform**: 跨平台开发
- **Jetpack Compose**: 声明式UI
- **Kotlinx DateTime**: 日期时间处理
- **Material Design 3**: 设计系统

## 开发计划

- [ ] 数据持久化(SQLDelight)
- [ ] 云端同步
- [ ] 通知提醒
- [ ] 主题切换
- [ ] 多语言支持