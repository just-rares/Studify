# Project Title

## Authors

| Name                                               | Email                     | Picture |
|----------------------------------------------------|---------------------------| ------------- |
| [Rares Burghelea](https://github.com/just-rares)   | rares.burghelea@gmail.com | <img src="https://avatars.githubusercontent.com/u/113239883?v=4" style="max-height: 100px;">
| [Razvan Gherasa](https://github.com/razvangherasa) | razvan_gherasa@yahoo.com       | <img src="https://avatars.githubusercontent.com/u/104271984?v=4" style="max-height: 100px;">

## Description

A brief description of what the project does.

## Features

The project includes the following features:

1. Feature 1
    - Description of how to use feature 1
2. Feature 2
    - Description of how to use feature 2
3. Feature 3
    - Description of how to use feature 3

<hr>

## Project Settings
Run configuration for the project

```xml
<component name="ProjectRunConfigurationManager">
  <configuration default="false" name="Run" type="Application" factoryName="Application">
    <option name="MAIN_CLASS_NAME" value="server.main.DemoApplication" />
    <module name="demo" />
    <option name="VM_PARAMETERS" value="--module-path &quot;C:\Users\rares\Desktop\demo-project\javafx-sdk-20\lib&quot; --add-modules=javafx.controls,javafx.fxml" />
    <method v="2">
      <option name="Make" enabled="true" />
    </method>
  </configuration>
</component>
```
<hr>

Install maven + check installation :
```shell
choco install maven
```
```shell
mvn -version
```

<hr>

After installing maven and downloading the project, in order to install the dependencies, run this:
```shell
mvn clean install
```
<hr>

Build the project:

```shell
mvn package
```

Run tests:

```shell
mvn tests
```
