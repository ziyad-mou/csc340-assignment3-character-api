
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

    <nav>
        <a href="/">Home</a>
        <a href="/about">About</a>
        <a href="/characters/new">Add a Character</a>
    </nav>

    <#if successMessage??>
        <p class="success-message">
            ${successMessage}
        </p>
    </#if>

    <#nested>

</body>
</html>
</#macro>
