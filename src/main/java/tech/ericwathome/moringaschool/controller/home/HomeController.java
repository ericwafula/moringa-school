package tech.ericwathome.moringaschool.controller.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {
    @GetMapping
    public String homeEndpoint() {
        return "<div style='padding-left:48px;padding-right-48px;'>" +
                "<h1 style='text-align:center;margin-top:70px;'>Moringa School API Version 0.0.1</h1>" +
                "<p style='font-size:24px'>Below are the different end points that showcase how the API works</p>" +
                "<p style='font-size:24px;font-weight:bold;'>Student API</p>" +
                "<p>add student - <a href='https://moringa-school-eric.herokuapp.com/api/v1/students/student/new'>https://moringa-school-eric.herokuapp.com/api/v1/students/student/new</a></p>" +
                "<p>all students - <a href='https://moringa-school-eric.herokuapp.com/api/v1/students'>https://moringa-school-eric.herokuapp.com/api/v1/students</a></p>" +
                "<p>find student by id - <a href='https://moringa-school-eric.herokuapp.com/api/v1/students/student/id/1'>https://moringa-school-eric.herokuapp.com/api/v1/students/student/id/1</a></p>" +
                "<p>find student by email - <a href='https://moringa-school-eric.herokuapp.com/api/v1/students/student/email/eric@gmail.com'>https://moringa-school-eric.herokuapp.com/api/v1/students/student/email/eric@gmail.com</a></p>" +
                "<p>update student by id - <a href='https://moringa-school-eric.herokuapp.com/api/v1/students/student/id/1'>https://moringa-school-eric.herokuapp.com/api/v1/students/student/id/1</a></p>" +
                "<p>update student by email - <a href='https://moringa-school-eric.herokuapp.com/api/v1/students/student/email/eric@gmail.com'>https://moringa-school-eric.herokuapp.com/api/v1/students/student/email/eric@gmail.com</a></p>" +
                "<p>delete student by id - <a href='https://moringa-school-eric.herokuapp.com/api/v1/students/student/id/1'>https://moringa-school-eric.herokuapp.com/api/v1/students/student/id/1</a></p>" +
                "<p>delete student by email - <a href='https://moringa-school-eric.herokuapp.com/api/v1/students/student/email/eric@gmail.com'>https://moringa-school-eric.herokuapp.com/api/v1/students/student/email/eric@gmail.com</a></p>" +
                "<p style='font-size:24px;font-weight:bold;'>Course API</p>" +
                "<p>create new course<a href='https://moringa-school-eric.herokuapp.com/api/v1/courses/course/new'>https://moringa-school-eric.herokuapp.com/api/v1/courses/course/new</a></p>" +
                "<p>display all courses<a href='https://moringa-school-eric.herokuapp.com/api/v1/courses'>https://moringa-school-eric.herokuapp.com/api/v1/courses</a></p>" +
                "<p>find course by id<a href='https://moringa-school-eric.herokuapp.com/api/v1/courses/course/id/1'>https://moringa-school-eric.herokuapp.com/api/v1/courses/course/id/1</a></p>" +
                "<p>find course by name<a href='https://moringa-school-eric.herokuapp.com/api/v1/courses/course/name/android'>https://moringa-school-eric.herokuapp.com/api/v1/courses/course/name/android</a></p>" +
                "<p>update course by id<a href='https://moringa-school-eric.herokuapp.com/api/v1/courses/course/id/1'>https://moringa-school-eric.herokuapp.com/api/v1/courses/course/id/1</a></p>" +
                "<p>update course by name<a href='https://moringa-school-eric.herokuapp.com/api/v1/courses/course/name/android'>https://moringa-school-eric.herokuapp.com/api/v1/courses/course/name/android</a></p>" +
                "<p>delete course by id<a href='https://moringa-school-eric.herokuapp.com/api/v1/courses/course/id/1'>https://moringa-school-eric.herokuapp.com/api/v1/courses/course/id/1</a></p>" +
                "<p>delete course by name<a href='https://moringa-school-eric.herokuapp.com/api/v1/courses/course/name/android'>https://moringa-school-eric.herokuapp.com/api/v1/courses/course/name/android</a></p>" +
                "<p>add student to course<a href='https://moringa-school-eric.herokuapp.com/api/v1/courses/course/id/1/students/student/new'>https://moringa-school-eric.herokuapp.com/api/v1/courses/course/id/1/students/student/new</a></p>" +
                "<p>assign technical mentor to course<a href='https://moringa-school-eric.herokuapp.com/api/v1/courses/course/id/1/technical-mentors/technical-mentor/id/1/new'>https://moringa-school-eric.herokuapp.com/api/v1/courses/course/id/1/technical-mentors/technical-mentor/id/1/new</a></p>" +
                "<p>list all students enrolled in course<a href='https://moringa-school-eric.herokuapp.com/api/v1/courses/course/id/1/students'>https://moringa-school-eric.herokuapp.com/api/v1/courses/course/id/1/students</a></p>" +
                "<p>list all technical mentors assigned to course<a href='https://moringa-school-eric.herokuapp.com/api/v1/courses/course/id/1/technical-mentors'>https://moringa-school-eric.herokuapp.com/api/v1/courses/course/id/1/technical-mentors</a></p>" +
                "<p style='font-size:24px;font-weight:bold;'>Technical Mentor API</p>" +
                "<p>add new technical mentors<a href='https://moringa-school-eric.herokuapp.com/api/v1/technical-mentors/technical-mentor/new'>https://moringa-school-eric.herokuapp.com/api/v1/technical-mentors/technical-mentor/new</a></p>" +
                "<p>get all technical mentors<a href='https://moringa-school-eric.herokuapp.com/api/v1/technical-mentors'>https://moringa-school-eric.herokuapp.com/api/v1/technical-mentors</a></p>" +
                "<p>find technical mentor by id<a href='https://moringa-school-eric.herokuapp.com/api/v1/technical-mentors/technical-mentor/id/1'>https://moringa-school-eric.herokuapp.com/api/v1/technical-mentors/technical-mentor/id/1</a></p>" +
                "<p>displays a list of technical mentors by corresponding characters in their name<a href='https://moringa-school-eric.herokuapp.com/api/v1/technical-mentors/technical-mentor/name/gi'>https://moringa-school-eric.herokuapp.com/api/v1/technical-mentors/technical-mentor/name/gi</a></p>" +
                "<p>update technical mentor by id<a href='https://moringa-school-eric.herokuapp.com/api/v1/technical-mentors/technical-mentor/id/1'>https://moringa-school-eric.herokuapp.com/api/v1/technical-mentors/technical-mentor/id/1</a></p>" +
                "<p>delete technical mentor by id<a href='https://moringa-school-eric.herokuapp.com/api/v1/technical-mentors/technical-mentor/id/1'>https://moringa-school-eric.herokuapp.com/api/v1/technical-mentors/technical-mentor/id/1</a></p>" +
                "<p>assign course to technical mentor<a href='https://moringa-school-eric.herokuapp.com/api/v1/technical-mentors/technical-mentor/id/1/courses/course/id/1/new'>https://moringa-school-eric.herokuapp.com/api/v1/technical-mentors/technical-mentor/id/1/courses/course/id/1/new</a></p>" +
                "<p>list all courses assigned to technical mentor<a href='https://moringa-school-eric.herokuapp.com/api/v1/technical-mentors/technical-mentor/id/1/courses'>https://moringa-school-eric.herokuapp.com/api/v1/technical-mentors/technical-mentor/id/1/courses</a></p>" +
                "</div>";
    }
}
