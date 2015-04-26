### Common tasks and activities
* Multiple jars
* Dependencies and versions
* Project structure
* Building, publishing and deploying

#### Archetype Info
* create directory structure

#### Dependency Info

### What Maven Do
Maven Repository

#### Project Template (Archetype)
1. create an archetype
`archetype:generate`

  * Group ID:
  * Artifact ID:
  * Version: 
  * Package:

##### The `pom.xml` file
* Maven co-ordinates
* Metadata
* Build information
* Resources and Dependencies

```xml
	<groupId></groupId>
	<artifactId></artifactId>
	<version></version>
	<packaging></packaging>

	<name></name>
	<url></url>

	<!-- when to use the dependency -->
	<dependencies>
		<dependency>
			<scope></scope>
		</dependency>
	<dependencies>
```

#### Maven Build (compile and package)
* Previous phases need to be run successfully in order to run current phase.

##### Build Lifecycle Phases
* validate
* compile
* test
* package
* install: publish an artifect to a local maven repository.
* deploy: publish an artifect to remote maven repository.











