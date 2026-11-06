<#import "layout.ftlh" as layout>

<@layout.page title="Marvel Character Gallery">

    <h1>Marvel Character Gallery</h1>

    <p>Explore some of Marvel's most iconic heroes.</p>

    <!-- Display characters from the database -->
    <#if characters?has_content>

        <#list characters as character>

            <div class="character-card">

                <!-- Character Image -->
                <#if character.imageUrl?has_content>

                    <img
                        src="${character.imageUrl}"
                        alt="${character.name}"
                    >

                <#else>

                    <div style="height: 300px;
                                background-color: #ddd;
                                display: flex;
                                align-items: center;
                                justify-content: center;
                                border-radius: 8px;">
                        No image available
                    </div>

                </#if>

                <!-- Character Name -->
                <h2>${character.name}</h2>

                <!-- Character Information -->
                <p>
                    <strong>Universe:</strong>
                    ${character.universe!'Unknown'}
                </p>

                <p>
                    <strong>Role:</strong>
                    ${character.role!'Unknown'}
                </p>

                <p>
                    <strong>Power Level:</strong>
                    ${character.powerLevel!'Unknown'}
                </p>

                <!-- Character Description -->
                <p>
                    ${character.description?truncate(140)}
                </p>

                <!-- Details Page -->
                <a href="/characters/${character.characterId?c}">
                    View Details
                </a>

            </div>

        </#list>

    <#else>

        <p>No characters found.</p>

    </#if>

</@layout.page>
