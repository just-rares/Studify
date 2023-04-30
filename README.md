# Project Title

## Authors

| Name                                               | Email                     | Picture                                                                          |
|----------------------------------------------------|---------------------------|----------------------------------------------------------------------------------|
| [Rares Burghelea](https://github.com/just-rares)   | rares.burghelea@gmail.com | <img src="https://avatars.githubusercontent.com/u/113239883?v=4" height="100px"> |
| [Razvan Gherasa](https://github.com/razvangherasa) | razvan_gherasa@yahoo.com  | <img src="https://avatars.githubusercontent.com/u/104271984?v=4" height="100px"> |

## Description

One of the most exciting features of Studify is the leveling system, which is designed to motivate users to achieve their goals and gain experience with each completed task.
Users start at level 1 and gain experience points by completing tasks and achieving their goals.
As they level up, they unlock new features and abilities, which helps keep them motivated to continue learning and growing.

In addition to the leveling system, Studify also provides users with a comprehensive dashboard that tracks their progress and displays detailed statistics about their activities.
Users can see how much time they've spent on each activity, how many goals they've achieved, and how many experience points they've earned.

Another great feature of Studify is the ability to add friends and connect with other users.
This feature allows users to see their friends' profiles and activities, which can help motivate them to stay on track and achieve their goals.
Users can also compete with their friends and see who can achieve the most experience points in a given period.
<hr>

## Project Settings
Run configuration for the project

```xml

<component name="ProjectRunConfigurationManager">
   <configuration default="false" name="Run" type="Application" factoryName="Application">
      <option name="MAIN_CLASS_NAME" value="server.main.MainApplication"/>
      <module name="demo"/>
      <option name="VM_PARAMETERS"
              value="--module-path &quot;C:\Users\rares\Desktop\demo-project\javafx-sdk-20\lib&quot; --add-modules=javafx.controls,javafx.fxml"/>
      <method v="2">
         <option name="Make" enabled="true"/>
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
