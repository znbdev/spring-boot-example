mysql-phpmyadmin
=====

mysql podman example

# Requirement

None

# Installation

```
podman-compose up -d
```

# Note

* [phpMyAdmin](http://localhost:9081/)

| user |   Password   |
|------|:------------:|
| root | YourPassword |
| test | YourPassword |

* Default schema

```
test_schema
```

# Create a new database named test

```sql
CREATE DATABASE `test`
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
```

# Problem

在修改了 docker-compose.yml 中 MySQL 和 phpMyAdmin 的密码后，重新运行 `podman-compose up`
应该生效新的密码。如果您仍然使用原来的密码，可能是因为容器的数据卷没有重新挂载。

您可以尝试使用以下命令重新挂载数据卷:

```shell script
podman-compose down -v

```

-v 参数将删除并重新创建数据卷，这将重新加载您在 docker-compose.yml 中更改的密码。