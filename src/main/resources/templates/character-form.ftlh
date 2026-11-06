
<#import "layout.ftlh" as layout>

<@layout.page title=formTitle>

    <h1>${formTitle}</h1>

    <p class="intro">
        Enter the character information below.
    </p>

    <form
        class="character-form"
        action="${formAction}"
        method="post"
    >

        <!-- Name -->
        <label for="name">Name *</label>

        <input
            id="name"
            name="name"
            type="text"
            maxlength="255"
            value="${(character.name)!''}"
            required
        >

        <#if validationErrors?? && validationErrors["name"]??>
            <p class="field-error">
                ${validationErrors["name"]}
            </p>
        </#if>

        <!-- Description -->
        <label for="description">
            Description / Backstory *
        </label>

        <textarea
            id="description"
            name="description"
            rows="6"
            maxlength="2000"
            required
        >${(character.description)!''}</textarea>

        <#if validationErrors?? && validationErrors["description"]??>
            <p class="field-error">
                ${validationErrors["description"]}
            </p>
        </#if>

        <!-- Universe -->
        <label for="universe">Universe</label>

        <input
            id="universe"
            name="universe"
            type="text"
            value="${(character.universe)!''}"
            placeholder="Marvel"
        >

        <!-- Role -->
        <label for="role">Role</label>

        <input
            id="role"
            name="role"
            type="text"
            value="${(character.role)!''}"
            placeholder="Superhero"
        >

        <!-- Power Level -->
        <label for="powerLevel">Power Level</label>

        <input
            id="powerLevel"
            name="powerLevel"
            type="number"
            min="0"
            max="100"
            value="<#if character.powerLevel??>${character.powerLevel?c}</#if>"
        >

        <#if validationErrors?? && validationErrors["powerLevel"]??>
            <p class="field-error">
                ${validationErrors["powerLevel"]}
            </p>
        </#if>

        <!-- Image URL -->
        <label for="imageUrl">Image URL</label>

        <input
            id="imageUrl"
            name="imageUrl"
            type="text"
            value="${(character.imageUrl)!''}"
            placeholder="/images/spider-man.jpg"
        >

        <!-- Submit -->
        <button type="submit">
            <#if editing>
                Save Changes
            <#else>
                Add Character
            </#if>
        </button>

        <a class="text-link" href="/">
            Cancel
        </a>

    </form>

</@layout.page>
