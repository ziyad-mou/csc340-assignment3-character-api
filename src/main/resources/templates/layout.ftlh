
<#macro page title>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>${title}</title>

    <link rel="stylesheet" href="/css/style.css">
</head>

<body>

    <nav aria-label="Main navigation">
        <a href="/">Home</a>
        <a href="/about">About</a>
        <a href="/characters/new">Add a Character</a>
    </nav>

    <main>

        <#if successMessage??>
            <p class="flash-success" role="status">
                ${successMessage}
            </p>
        </#if>

        <#nested>

    </main>

</body>
</html>
</#macro>
