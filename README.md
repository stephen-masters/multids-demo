Spring Data multiple datasource demo
====================================

This project shows how to use Spring Data JPA repositories with more than one datasource.
There are two embedded HSQLDB databases, `foodb` and `bardb`. Each has its own entity
manager factory, transaction manager and repository package:

| Datasource | Entities | Repositories | Configuration |
|---|---|---|---|
| `fooDataSource` | `com.sctrcd.multidsdemo.domain.foo` | `...integration.repositories.foo` | `FooConfig` |
| `barDataSource` | `com.sctrcd.multidsdemo.domain.bar` | `...integration.repositories.bar` | `BarConfig` |

Spring Boot's own datasource and JPA auto-configuration back off once there is more than one
`DataSource` and none is marked `@Primary`, so each one is wired explicitly. The tests check that
an entity saved through one repository lands in its own database and not in the other.

It needs Java 25 and uses Spring Boot 4. The Maven wrapper downloads Maven itself, so to run a
full build, including the format check and the tests:

    ./mvnw clean verify

If the format check fails, `./mvnw spotless:apply` fixes it. To install the git hooks, which
check formatting before each commit and run the build before each push:

    lefthook install

See [AGENTS.md](AGENTS.md) for the conventions and [SECURITY.md](SECURITY.md) for reporting a
vulnerability.
