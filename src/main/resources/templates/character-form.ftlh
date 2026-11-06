<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add a Character</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>

    <nav>
        <a href="index.html">Home</a>
        <a href="details.html">Details</a>
        <a href="about.html">About</a>
        <a href="new-character-form.html">Add a Character</a>
    </nav>

    <h1>Add a New Character</h1>

    <form>

        <label for="name">Character Name:</label>
        <input type="text" id="name" name="name">

        <label for="universe">Universe:</label>
        <input type="text" id="universe" name="universe">

        <label for="role">Role:</label>
        <input type="text" id="role" name="role">

        <label for="image">Image URL:</label>
        <input type="url" id="image" name="image">

        <label for="description">Short Description:</label>
        <textarea id="description" name="description" rows="5"></textarea>

        <button type="submit">Add Character</button>

    </form>

</body>

</html>