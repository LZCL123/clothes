# 🪞 浮窗衣镜 — AI 虚拟试衣平台(后端·用户与数据服务)

[![Java](https://img.shields.io/badge/Java-8-orange)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7-brightgreen)](https://spring.io/projects/spring-boot)
[![MyBatis-Plus](https://img.shields.io/badge/MyBatis--Plus-3.5-blue)](https://baomidou.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

「浮窗衣镜」AI 悬浮虚拟试衣平台的后端服务之一。**本仓库为「用户认证与数据服务」**,负责账号体系、用户数据与文件存储;AI 试衣/推荐能力由独立的 Python 模型服务提供(团队协作,源码未包含在本仓库)。

> 🏆 **2025 年华北五省计算机应用大赛 省级一等奖** · 2025 年计算机设计大赛 省级二等奖
> 参赛作品(本人负责后端开发、小程序前端开发、架构设计与统筹答辩)

## 🏗️ 系统架构

```
微信小程序 (uni-app, 见姊妹仓库 clothes-miniapp)
    ├──→ 本仓库:用户认证与数据服务 (Spring Boot)
    │        登录/注册(短信验证码) · JWT 鉴权 · 用户信息 · 身体测量数据 · 文件上传
    └──→ 模型服务 (Python, 团队协作)
             虚拟试衣(Leffa) · 衣服推荐
```

## ✨ 本仓库能力

- **用户体系** — 注册登录(阿里云短信验证码)、JWT 鉴权、登录用户信息
- **数据管理** — 用户信息维护、身体测量数据(用于试衣尺码推荐)
- **文件存储** — MinIO 对象存储 + 阿里云 OSS 双通道,图片上传(10MB 单文件 / 100MB 请求)
- **工程规范** — 统一 Result 响应、全局异常处理、认证拦截器、knife4j 接口文档

## 🛠️ 技术栈

| 分类 | 技术 |
| --- | --- |
| 框架 | Spring Boot 2.7、Maven 多模块 |
| 持久层 | MyBatis-Plus、MySQL、HikariCP |
| 鉴权 | JJWT(HS256,密钥环境变量注入) |
| 存储 | MinIO、阿里云 OSS |
| 工具 | knife4j、Lombok、easy-captcha |
| 消息 | 阿里云短信 SDK |

## 🏗️ 模块结构

```
├── clothes-common/    # 通用工具、异常、Result 封装、JWT、OSS/SMS 配置
├── clothes-model/     # 实体与 VO
└── clothes-web/       # Web 入口(登录/测量/文件上传控制器、拦截器、配置)
```

## 🚀 快速开始

### 1. 环境要求

JDK 8+、Maven 3.6+、MySQL、Redis、MinIO(本地开发可 docker-compose 一键启动)。

### 2. 配置环境变量(可选)

敏感配置均支持环境变量注入,未配置时使用本地开发默认值:

| 变量 | 说明 |
| --- | --- |
| `DB_URL` / `DB_USERNAME` / `DB_PASSWORD` | MySQL 连接 |
| `REDIS_PASSWORD` | Redis 密码 |
| `JWT_SECRET` | JWT 签名密钥(生产必须配置) |
| `MINIO_ENDPOINT` / `MINIO_ACCESS_KEY` / `MINIO_SECRET_KEY` | MinIO 对象存储 |
| `ALIYUN_SMS_*` | 阿里云短信(可选) |
| `ALIYUN_OSS_*` | 阿里云 OSS(可选) |

### 3. 启动

```bash
mvn -pl clothes-web -am spring-boot:run
```

启动后访问:
- 服务地址:`http://localhost:8080`
- 接口文档:`http://localhost:8080/doc.html`(knife4j)

## 📌 说明

- 前端源码见姊妹仓库 [`LZCL123/clothes-miniapp`](https://github.com/LZCL123/clothes-miniapp)
- 本仓库为参赛作品的后端服务之一,AI 试衣/推荐模型由团队协作完成,模型源码未包含在本仓库
