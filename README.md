# Moringa School API
#### Moringa School API for SD/TM apprentice position interview, 7/03/2022
#### By Eric Wafula
## Description
This is a Springboot/SpringMVC API that simulates how Students and their respective Technical Mentors are enrolled in Moringa School.
The api has been implemented following RestFul principles and has all the basic CRUD operations.
I've grouped the API into different submodules that contain individual API's with their respective endpoints
##### Below are some of the endpoints in the api:
###Student API
##### add new student - https://moringa-school-eric.herokuapp.com/api/v1/students/student/new
##### list all students - https://moringa-school-eric.herokuapp.com/api/v1/students
##### find student by id - https://moringa-school-eric.herokuapp.com/api/v1/students/student/id/{studentId}
##### update student by id - https://moringa-school-eric.herokuapp.com/api/v1/students/student/id/{studentId}
##### delete student by id - https://moringa-school-eric.herokuapp.com/api/v1/students/student/id/{studentId}

 To get the full list of API's and their respective endpoints, follow this link: [https://moringa-school-eric.herokuapp.com/](https://moringa-school-eric.herokuapp.com/)

## Setup/Installation Requirements
* git clone https://github.com/ericwafula/moringa-school.git
* open with preferred IDE or text editor. I recommend using Intellij IDEA
* sync project
* click run application to start a local tomcat server
* the application uses java 17, so it should be compatible with the java version you are using. If you wish to use a different JDK version however, you can change the project's java version in the pom xml
## Known Bugs
if any issue arises, I'll make sure to mention them otherwise I've encountered none at the moment.
## Technologies Used
JDK17, Springboot, PostgreSQL
## Support and contact details
You can reach out via ericwathome007@gmail.com
### License
[MIT](license.txt)
Copyright (c) 2022 **Eric Wafula** Project Files