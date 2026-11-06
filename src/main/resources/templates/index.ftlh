
<#import "layout.ftlh" as layout>

<@layout.page title="Marvel Character Gallery">

    <h1>Marvel Character Gallery</h1>

    <p class="intro">
        Explore some of Marvel's most iconic heroes.
    </p>

    <!-- Search and Filter -->
    <form class="search-form" method="get" action="/">

        <div>
            <label for="search-name">Search by name</label>
            <input
                id="search-name"
                type="text"
                name="name"
                value="${nameQuery!''}"
                placeholder="e.g. Spider-Man"
            >
        </div>

        <div>
            <label for="search-universe">
                Filter by universe
            </label>
            <input
                id="search-universe"
                type="text"
                name="universe"
                value="${universeQuery!''}"
                placeholder="e.g. Marvel"
            >
        </div>

        <button type="submit">Search</button>

        <a class="text-link" href="/">
            Clear filters
        </a>

    </form>

    <!-- Character Gallery -->
    <#if characters?has_content>

        <div class="gallery-grid">

            <#list characters as character>

                <article class="character-card">

                    <!-- Character Image -->
                    <img
                        src="${(character.imageUrl?has_content)?then(character.imageUrl, '/images/placeholder.svg')}"
                        alt="${character.name}"
                        onerror="this.onerror=null;this.src='/images/placeholder.svg';"
                    >

                    <!-- Character Name -->
                    <h2>${character.name}</h2>

                    <!-- Character Information -->
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

                    <!-- Short Description -->
                    <p>
                        ${character.description?truncate(70)}
                    </p>

                    <!-- View Details -->
                    <a href="/characters/${character.characterId?c}">
                        View Details
                    </a>

                </article>

            </#list>

        </div>

    <#else>

        <p class="empty-state">
            No characters found.
        </p>

    </#if>

</@layout.page>
