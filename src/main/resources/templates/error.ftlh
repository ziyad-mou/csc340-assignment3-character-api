
<#import "layout.ftlh" as layout>

<@layout.page title=errorTitle>

    <section class="empty-state error-state">

        <h1>${errorTitle}</h1>

        <p>${errorMessage}</p>

        <a class="action-link" href="/">
            Return to the Gallery
        </a>

    </section>

</@layout.page>
