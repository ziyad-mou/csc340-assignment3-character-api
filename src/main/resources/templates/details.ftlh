
<#import "layout.ftlh" as layout>

<@layout.page title=character.name + " - Character Details">

    <section class="details-card">

        <!-- Larger Character Image -->
        <img
            class="details-image"
            src="${(character.imageUrl?has_content)?then(character.imageUrl, '/images/placeholder.svg')}"
            alt="${character.name}"
            onerror="this.onerror=null;this.src='/images/placeholder.svg';"
        >

        <div class="details-content">

            <h1>${character.name}</h1>

            <p>
                <strong>Character ID:</strong>
                ${character.characterId?c}
            </p>

            <p>
                <strong>Universe:</strong>
                ${(character.universe?has_content)?then(character.universe, 'Unknown')}
            </p>

            <p>
                <strong>Role:</strong>
                ${(character.role?has_content)?then(character.role, 'Unknown')}
            </p>

            <p>
                <strong>Power Level:</strong>

                <#if character.powerLevel??>
                    ${character.powerLevel?c}
                <#else>
                    Unknown
                </#if>
            </p>

            <h2>Full Description</h2>

            <p>
                ${character.description}
            </p>

            <div class="details-actions">

                <!-- Edit -->
                <a
                    class="action-link"
                    href="/characters/${character.characterId?c}/edit"
                >
                    Edit Character
                </a>

                <!-- Delete -->
                <form
                    class="delete-form"
                    method="post"
                    action="/characters/${character.characterId?c}/delete"
                    onsubmit="return confirm('Are you sure you want to delete this character?');"
                >
                    <button
                        class="danger-button"
                        type="submit"
                    >
                        Delete Character
                    </button>
                </form>

                <!-- Back to Gallery -->
                <a
                    class="action-link secondary"
                    href="/"
                >
                    Back to Gallery
                </a>

            </div>

        </div>

    </section>

</@layout.page>
