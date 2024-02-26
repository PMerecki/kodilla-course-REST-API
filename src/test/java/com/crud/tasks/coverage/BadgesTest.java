package com.crud.tasks.coverage;

import com.crud.tasks.domain.AttachmentsByType;
import com.crud.tasks.domain.Badges;
import com.crud.tasks.domain.Trello;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BadgesTest {

    @Test
    public void testSerializationAndDeserialization() throws Exception {
        // Given
        Badges badges = new Badges();
        badges.setVotes(5);

        AttachmentsByType attachmentsByType = new AttachmentsByType();
        Trello trello = new Trello();
        trello.setBoard(1);
        trello.setCard(2);
        attachmentsByType.setTrello(trello);

        badges.setAttachmentsByType(attachmentsByType);

        // ObjectMapper to serialize and deserialize objects
        ObjectMapper objectMapper = new ObjectMapper();

        // When
        String json = objectMapper.writeValueAsString(badges);
        Badges deserializedBadges = objectMapper.readValue(json, Badges.class);

        // Then
        assertEquals(badges, deserializedBadges);
    }
}
