# test-spark (temporary name)

Boilerplate code when trying out Spark Framework

## Libraries

* **Spark Framework** (not Apache Spark) - Routing libraries. Uses Java 8 syntax.
* **Google Guice** - Will never write in Java without any dependency injection.
* **Liquibase** - Has rollback.
* **Jdbi** - I'm familiar with SQL. Don't need ORM. I can't copy paste jOOQ's syntax to test some query.
* **Lombok** - I'm just that lazy.
* **Google Guava** - Immutable collections.
* **Gson** - Nicer API.
* **Caffeine** - Was part of Guava, so...
* **HikariCP** - Must have for any database-backed app.
* **Junit** - Duh.
* **SLF4J** - Spark Framework's default.

## Known issues

* For Liquibase changelog, the schema version need to be the same with Liquibase version.
* HikariCP can't use MySQLDataSource for the config. Configure using jdbcUrl instead.
* There is no automatic `@OneToMany` mapping for Jdbi like in ORM. Just write a mapper for it.
* When having collections as a field in the POJO, Gson didn't map an empty collection when there is no data, it simply gives null.

## License

```
   Copyright 2017 Burhanuddin Baharuddin

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
