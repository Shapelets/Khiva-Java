# README #
This is the Khiva binding for connecting the Java programming language and the Khiva library.

## License
This project is licensed under [MPL-v2](https://www.mozilla.org/en-US/MPL/2.0/).
 
## Quick Summary
This Java package called 'khiva' provides all the functionalities of the Khiva library for time series analytics.

## Requirements
* Arrayfire
* Khiva library

## Set up
It is just needed to execute the next command in the root directory of the project:
```bash
mvn install
```
## Executing the tests:
Execute the next command in the root directory of the project:
```bash
 mvn test
```
 
Note: The tests are executed automatically when the package is installed.

## Documentation
This Java package follows the standard way of writing documentation of Java code using Javadoc.

In order to generate the documentation, execute the next command in the root directory of the project: 
```bash
mvn javadoc:javadoc
```

## Contributing

### Branching model
Our branching model has two permanent branches, **develop** and **master**. 
We aim at using `develop` as the main branch, where all features are merged. 
In this sense, we use the master branch to push the release versions of the binding for the Khiva library.

### Contribution process
In order to contribute to the code base, we follow the next process:
1. The main branch is `develop`, every developer should pull the current status of the branch before starting to develop any new feature.
`git pull`
2. Create a new branch with the following pattern "feature/[name_of_the_feature]"
`git checkout -b feature/exampleFeature`
3. Develop the new feature on the the new branch. It includes testing and documentation.
`git commit -a -m "Bla, Bla, Bla";  git push`
4. Open a Pull Request to merge the feature branch in to develop. Currently, a pull request has to be reviewed at least by one person.
5. Finally, delete the feature branch.
6. Move back to develop branch.
`git checkout develop`
7. Pull the latest changes.
`git pull`
