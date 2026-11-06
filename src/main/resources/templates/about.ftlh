
<#import "layout.ftlh" as layout>

<@layout.page title="About - Marvel Character Gallery">

    <section class="about-content">

        <h1>About the Marvel Character Gallery</h1>

        <img
            class="about-image"
            src="/images/iron-man.jpg"
            alt="Iron Man in his armor"
        >

        <p>
            The Marvel Character Gallery celebrates
            some of the most memorable superheroes
            from the Marvel universe.
        </p>

        <p>
            Visitors can explore different characters,
            learn about their powers and stories,
            search for characters, and view detailed
            character information.
        </p>

        <p>
            The application also allows users to
            add new characters, edit existing characters,
            and delete characters from the database.
        </p>

        <h2>How This Application Works</h2>

        <p>
            This project combines the HTML and CSS
            design from Assignment 2 with the
            Spring Boot REST API and Neon PostgreSQL
            database from Assignment 3.
        </p>

        <p>
            When a visitor opens a page, Spring Boot
            receives an HTTP request through a
            Page Controller.
        </p>

        <p>
            The Controller calls the Service, which
            communicates with a JPA Repository to
            retrieve or update information in the
            Neon PostgreSQL database.
        </p>

        <p>
            The Controller places the data inside
            a Model. FreeMarker then combines
            that information with HTML templates
            to generate the completed website.
        </p>

        <h2>REST API</h2>

        <p>
            The application also maintains a REST API
            at <code>/api/characters</code>.
            The REST API returns JSON and shares
            the same Service and database.
        </p>

    </section>

</@layout.page>
