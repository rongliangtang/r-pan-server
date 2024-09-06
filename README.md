# R-Pan
R-Pan 是一个**简单易用、支持分布式存储**的网盘系统。

**"R"** 取自我的名字，用以代表该项目。

这个仓库为后端代码，前端代码仓库地址可以在 [R-Pan-Portal](https://github.com/rongliangtang/r-pan-portal) 找到。

[在线体验地址](https://pan.tangrl.cn)

## 功能
目前已经实现下面这些功能：

- 支持用户注册、登录及密码修改。
- 提供高性能的文件上传、下载和分享功能。
- 支持分片上传和秒传技术，提升上传效率。
- 提供回收站功能，便于恢复误删文件。
- 支持多种存储方式，包括本地存储、OSS 对象存储和 FastDFS 分布式存储。
- 实现视频、图片、PDF 等文件的在线预览功能。

## 开发环境
| **组件** | **版本**      | **说明**                                              |
|--------|---------------|-------------------------------------------------------|
| JDK    | 8             | Java 开发环境                                          |
| IDEA   | 2024.1        | 主要的开发工具                                         |
| Maven  | 3.6.3         | 构建和依赖管理工具                                     |
| Spring Boot | 2.2.5         | 用于快速构建后端服务的 Spring 框架                     |
| MySQL  | 8.0.33        | 关系型数据库管理系统                                   |
| Redis  | 7.0.11        | 缓存和数据存储的内存数据库                             |
| RocketMQ | 4.5.1         | 分布式消息中间件，支持高吞吐的消息处理                 |
| ZooKeeper | 3.5.3         | 分布式协调和管理组件，支持服务发现和配置管理           |
| FastDFS | -             | 分布式文件存储系统，提供高效的文件管理服务             |

## 系统架构

<img src="https://blog-1259405505.cos.ap-guangzhou.myqcloud.com/arch.png" width="500">

## 如何运行
以下是本地运行该后端服务的步骤：

### 步骤 1：克隆项目代码
使用以下命令克隆后端服务的源代码：

```shell
git clone https://github.com/rongliangtang/r-pan-server.git
```

### 步骤 2：配置环境
根据操作系统，按照开发环境的要求进行配置。
可以参考各组件的官方文档或相关资料完成环境设置。

### 步骤 3：创建数据库
创建一个名为 `r_pan` 的数据库，并执行 `distribution/conf/db.sql` 文件中的 SQL 语句以创建所需的数据表。


### 步骤 4：配置消息中间件
完成 RocketMQ 和 Dashboard 的安装后，创建以下主题（Topics）：`testTopic`、`errorLogTopic`、`deleteFileTopic`、`fileRestoreTopic`、`physicalDeleteFileTopic`、`userSearchTopic`。

### 步骤 5：修改项目配置文件
根据本地环境，修改 `server/resource/application.yaml` 文件中的相关配置参数，确保项目适应当前的系统环境。

## 界面示例
### 登录
![](https://blog-1259405505.cos.ap-guangzhou.myqcloud.com/20240906112645.png)

### 文件列表
![](https://blog-1259405505.cos.ap-guangzhou.myqcloud.com/20240906112706.png)

### 文件分享
![](https://blog-1259405505.cos.ap-guangzhou.myqcloud.com/20240906112821.png)

### 回收站
![](https://blog-1259405505.cos.ap-guangzhou.myqcloud.com/20240906112749.png)

## TODO
- 前端
  - 优化 UI 和布局
- 后端
  - 实现文件夹的读取和下载
  - 支持 MinIO
  - 拆分成微服务，引入 Spring Cloud 网关、链路追踪、相关组件、服务治理等组件
- 部署
  - 编写 dockerfile，实现一键部署
  - 实现 GitOps，基于 Jenkins 实现 CI/CD
  - 部署到 K8S 集群