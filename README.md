# Menthol
<p>Menthol - Spring Boot Platform for Mentors and Mentees - 2020 (Spring, Hibernate) (Java EE)</p>

<p>The platform is designed for mentors and mentees and has two roles. One of the roles is the user role. Mentors and mentees can be a user. Another role is the admin role, and admins can control the system from the interface. With this platform, mentors and mentees can manage their relationships through a flexible online diary provided by the program.</p>

<h3>Back-End</h3>
<p>An embedded LDAP data storage solution is used to store user information. The information for this database is embedded in the project in the ".ldif" file. This file contains the full names, surnames, usernames, and e-mail information of the users. To authorize users, two groups, namely admin and user, have been defined and defined in this file. For users (User and Admin) to use the system features, the Hibernate H2 Database solution was used because it is portable and embedded. JPA Repository is preferred to be able to query tables and to transmit data correctly to the front-end side.</p>

<h3>Front-End</h3>
<p>Front-End opens with a welcome screen. This screen contains information about the platform. At the top right, there is a login form. This login form supports two different input methods. The first is the form field, which supports the login with the username and password, verified by the LDAP embedded server, and the other supports login with Google verification using OAUTH2. Users are previously defined and kept in the LDAP embedded server. Passwords are stored in the database by encrypting with SHA. The previously defined users information is as follows:</p>
<ul>
  <li>Admin: onur</li>
  <li>User: ali, ahmet, mehmet</li>
  <li>Common Pass: secret </li>
</ul>
<p>As seen above, there are two types of users in the system, and users cannot access pages and functions belonging to each other. Spring Boot, Spring Web, Thymeleaf, and Bootstrap are used for the design of the front-end. Interfaces are written in HTML, CSS, and JavaScript.</p>

<p>You can find more information about project in solution architecture article or in presentation but in Turkish.</p>
