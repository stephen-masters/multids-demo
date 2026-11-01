# AGENTS.md

Guidance for anyone working in this repository with a coding agent (Claude
Code, Codex, Cursor, Gemini CLI or whatever else reads this file) and a fair
summary of the house rules for contributors who use none.

## What this repository is

**multids-demo** is a small demonstration of Spring Data JPA repositories
over more than one datasource. It is a teaching example, not a library or an
application: nothing is released or deployed, and the code should stay as
short as the point it makes.

The point is this. With two `DataSource` beans and neither marked
`@Primary`, Spring Boot's datasource and JPA auto-configuration back off, so
each datasource needs its own entity manager factory, transaction manager
and `@EnableJpaRepositories` scoped to its own repository package. The tests
prove that an entity saved through one repository lands in its own database
and not in the other.

## Hard rules

### Keep the two sides symmetrical

`foo` and `bar` are deliberately identical apart from their names. A change
to one side (`FooConfig`, `Foo`, `FooRepository`, `FooRepositoryTest`) is
made to the other in the same commit. A reader should be able to diff them
and see only the names change.

### No `@Primary`

Marking either datasource primary would let Spring Boot's auto-configuration
quietly wire it as the default, which hides the very thing the demo shows.
Every bean that depends on a datasource or an entity manager factory names
the one it wants with `@Qualifier`.

### The isolation tests are the demo's claim

Each repository test asserts that its table exists in its own database and
not in the other. Do not weaken those assertions to make a change pass.

### Stay on current LTS versions

The project tracks the latest LTS JDK (`java.version` in `pom.xml`, and
`java-version` in `.github/workflows/ci.yml`; change both together) and the
latest Spring Boot GA release. Dependency versions come from the Spring Boot
parent's dependency management: do not pin a version that the parent
already manages.

## Layout

| Path | What |
|---|---|
| `src/main/java/.../integration/config/AppConfig.java` | `@SpringBootApplication`; the shared `JpaVendorAdapter` |
| `src/main/java/.../integration/config/foo/FooConfig.java` | `fooDataSource`, its entity manager factory and transaction manager |
| `src/main/java/.../integration/config/bar/BarConfig.java` | The same for `barDataSource` |
| `src/main/java/.../domain/{foo,bar}/` | One entity per datasource |
| `src/main/java/.../integration/repositories/{foo,bar}/` | One Spring Data repository per datasource |
| `src/test/java/.../integration/repositories/{foo,bar}/` | The isolation tests |
| `.githooks/commit-msg` | Conventional Commits check, shared from `scattercode/release-pipelines` |

## Checks

```bash
./mvnw spotless:apply   # format the Java sources and the POM
./mvnw verify           # everything below, and the tests
```

`./mvnw verify` runs, in order:

- the **enforcer**: the JDK is at least `java.version`, Maven is at least
  3.9, and dependency versions converge;
- the **Spotless** check (palantir-java-format for Java, sortpom for
  `pom.xml`) in the `validate` phase;
- the **compiler** with `-Xlint:all -Werror`, so any warning fails the build;
- the **tests**.

It needs JDK 25; the Maven wrapper fetches Maven itself. All of it must
pass before a commit. The lefthook hooks run the format check before each
commit and the full build before each push; CI runs the build and a Trivy
scan on every push and pull request.

## Conventions

- British English; sentence case for headings.
- Conventional Commits, enforced by `.githooks/commit-msg` and, for pull
  request titles, by `pr-title.yml`. Dependency updates are `build:`,
  workflow changes `ci:`.
- `.githooks/commit-msg` is a byte-for-byte copy of the template in
  `scattercode/release-pipelines`; CI checks it has not drifted. Change it
  there, not here.
- `main` is protected: changes arrive through pull requests with the CI
  checks passing.
- Hook setup in a fresh clone: `lefthook install`, or without lefthook,
  `git config core.hooksPath .githooks` for the commit message check alone.
