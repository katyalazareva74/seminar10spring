package ru.example.sem6homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.example.sem6homework.repository.NoteRepository;
import ru.example.sem6homework.model.Note;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class NoteServiceIntegrationTest {
    /**
     * Интеграционное тестирование
     */
    @MockBean
    private NoteRepository noteRepository;
    @Autowired
    private NoteService noteService;

    @Test
    @DisplayName("noteDeleteTest")
    public void noteDeleteTest() {
        // Предпосылки
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Day");
        note.setContent("A lot of work");
        given(noteRepository.findById(1L)).willReturn(Optional.of(note));
        // Вызов
        noteService.deleteNote(1L);
        // Проверка
        verify(noteRepository).deleteById(1L);
    }

    @Test
    @DisplayName("noteFindAllTest")
    public void noteFindByIdIntegrationTest() {
        // Предпосылки
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Day");
        note.setContent("A lot of work");
        when((Optional<Note>) noteRepository.findById(1L)).thenReturn(Optional.of(note));
        //Вызов
        Optional<Note> note1 = noteService.findById(1L);
        // Проверка
        verify(noteRepository).findById(1L);
        assertNotNull(note1);
        assertEquals(Optional.of(note), note1);
    }
}
