# department

Learning spring boot stuff with Bootify.io -- a delightful application genorator application

Fun URLs: 

1. http://localhost:8080/swagger-ui/index.html
2. http://localhost:8080/h2-console/


## Things to try

1. Do a POST to /api/fakers to geneerate sample data.

in the scripts folder there's some curls.  Try them in this order:

2. `create_department.sh`
3. `create_person.sh`
4. `work_some.sh`

## Things to look at
1. I'm trying to use pull requests, so please take a look at https://github.com/payne/department/pulls?q=is%3Apr+is%3Aclosed

## Liquidbase

1. https://github.com/liquibase/liquibase-gradle-plugin
2. Nice overview that is not 100% on point https://youtu.be/YhicwD489xQ
3. https://github.com/stevesaliman/liquibase-workshop is so OLD! Should I have chosen FlyWay??
4. Might be handy: https://github.com/daquino/liquify
5. 


### `gradle tasks` shows liquidbase tasks (and others)

```

> Task :tasks

------------------------------------------------------------
Tasks runnable from root project 'department'
------------------------------------------------------------

Application tasks
-----------------
bootRun - Runs this project as a Spring Boot application.

Build tasks
-----------
assemble - Assembles the outputs of this project.
bootBuildImage - Builds an OCI image of the application using the output of the bootJar task
bootJar - Assembles an executable jar archive containing the main classes and their dependencies.
bootJarMainClassName - Resolves the name of the application's main class for the bootJar task.
bootRunMainClassName - Resolves the name of the application's main class for the bootRun task.
build - Assembles and tests this project.
buildDependents - Assembles and tests this project and all projects that depend on it.
buildNeeded - Assembles and tests this project and all projects it depends on.
classes - Assembles main classes.
clean - Deletes the build directory.
jar - Assembles a jar archive containing the main classes.
testClasses - Assembles test classes.

Build Setup tasks
-----------------
init - Initializes a new Gradle build.
wrapper - Generates Gradle wrapper files.

Documentation tasks
-------------------
javadoc - Generates Javadoc API documentation for the main source code.

Help tasks
----------
buildEnvironment - Displays all buildscript dependencies declared in root project 'department'.
dependencies - Displays all dependencies declared in root project 'department'.
dependencyInsight - Displays the insight into a specific dependency in root project 'department'.
dependencyManagement - Displays the dependency management declared in root project 'department'.
help - Displays a help message.
javaToolchains - Displays the detected java toolchains.
outgoingVariants - Displays the outgoing variants of root project 'department'.
projects - Displays the sub-projects of root project 'department'.
properties - Displays the properties of root project 'department'.
tasks - Displays the tasks runnable from root project 'department'.

Liquibase tasks
---------------
calculateCheckSum - Calculate and print a checksum for the <liquibaseCommandValue> changeset.
changelogSync - Mark all changes as executed in the database.
changelogSyncSql - Output the raw SQL used by Liquibase when running changelogSync.
changelogSyncToTag - Mark all undeployed changesets up to and including the <liquibaseCommandValue> tag as executed in your database.
changelogSyncToTagSql - Output the raw SQL used by Liquibase when running the changelogSyncToTag command.
checks - Execute the <liquibaseCommandValue> quality check
clearChecksums - Remove all saved checksums from the database. On next run checksums will be recomputed.  Useful for "MD5Sum Check Failed" errors.
dbDoc - Generates Javadoc-like documentation for the existing database and changelogs to the <liquibaseCommandValue> directory.
deactivateChangeLog - Removes the changelogID from your changelog so it stops sending reports to Liquibase Hub.
diff - Compare two databases and write differences to STDOUT.
diffChangeLog - Compare two databases to produce changesets and write them to a changelog file
dropAll - Drop all database objects owned by the user. Note that functions, procedures and packages are not dropped (Liquibase limitation)
executeSql - Execute a SQL string or file given in <liquibaseCommandValue> in this format: -PliquibaseCommandValue="--sql=select 1" or -PliquibaseCommandValue="--sqlFile=myfile.sql"
futureRollbackCountSql - Generates SQL to sequentially revert <liquibaseCommandValue> changes the database.
futureRollbackFromTagSql - Generates SQL to revert future changes up to the <liquibaseCommandValue> tag.
futureRollbackSql - Generate the raw SQL needed to rollback undeployed changes.
generateChangelog - Generate a Groovu changelog from the current state of the database.
history - List all deployed changesets and their deployment IDs.
listLocks - List the hostname, IP address, and timestamp of the Liquibase lock record.
markNextChangesetRan - Mark the next change you apply as executed in the database.
markNextChangesetRanSql - Write SQL to mark the next change you apply as executed in the database.
registerChangeLog - Register the changelog with a Liquibase Hub project.
releaseLocks - Remove the Liquibase lock record from the DATABASECHANGELOG table.
rollback - Rollback changes made to the database since the the <liquibaseCommandValue> tag was applied.
rollbackCount - Rollback the last <liquibaseCommandValue> changes.
rollbackCountSql - Write SQL to roll back the last <liquibaseCommandValue> changes.
rollbackOneChangeSet - Roll back the specific <liquibaseCommandValue> changeset, without rolling back changesets deployed before or afterwards. (Liquibase Pro key required)
rollbackOneChangeSetSql - Write SQL to roll back the specific <liquibaseCommandValue> changeset, without rolling back changesets deployed before or afterwards. (Liquibase Pro key required)
rollbackOneUpdate - Rollback one update from the database (Liquibase Pro key required).
rollbackOneUpdateSql - Write SQL to rollback one update from the database (Liquibase Pro key required).
rollbackSql - Write SQL to roll back the database to the state it was in when the <liquibaseCommandValue> tag was applied.
rollbackToDate - Rollback changes made to the database since the <liquibaseCommandValue> date/time.
rollbackToDateSql - Write SQL to rollback changes made to the database since the <liquibaseCommandValue> date/time.
snapshot - Capture the current state of the database.
snapshotReference - Capture the current state of the reference database.
status - Generate a list of pending changesets.
syncHub - Synchronize the local DatabaseChangeLog table with Liquibase Hub.
tag - Mark the current database state with the <liquibaseCommandValue>.
tagExists - Verify the existence of the <liquibaseCommandValue> tag.
unexpectedChangeSets - Generate a list of changesets that have been executed but are not in the current changelog.
update - Deploy any changes in the changelog file that have not been deployed.
updateCount - Deploy the next <liquibaseCommandValue> changes from the changelog file.
updateCountSql - Generate the SQL to deploy the next <liquibaseCommandValue> changes from the changelog file..
updateSql - Writes SQL to update the database to the current version to STDOUT.
updateTestingRollback - Updates database, then rolls back changes before updating again. Useful for testing rollback support.
updateToTag - Deploy changes from the changelog file to the <liquibaseCommandValue> tag.
updateToTagSql - Generate the SQL to deploy changes from the changelog file to the <liquibaseCommandValue> tag
validate - Validate the changelog for errors.

Verification tasks
------------------
check - Runs all checks.
test - Runs the unit tests.

Rules
-----
Pattern: clean<TaskName>: Cleans the output files of a task.
Pattern: build<ConfigurationName>: Assembles the artifacts of a configuration.

To see all tasks and more detail, run gradle tasks --all

To see more detail about a task, run gradle help --task <task>

BUILD SUCCESSFUL in 944ms
1 actionable task: 1 executed

```
