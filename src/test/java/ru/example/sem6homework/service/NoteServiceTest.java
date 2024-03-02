package ru.example.sem6homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.example.sem6homework.model.Note;
import ru.example.sem6homework.repository.NoteRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class NoteServiceTest {
    /**
     * Тестирование отдельных методов приложения
     */
    @Test
    @DisplayName("noteCreateTest")
    public void noteSaveTest() {
        // Предпосылки
        NoteRepository noteRepository = mock(NoteRepository.class);
        NoteService noteService = new NoteService(noteRepository);

        Note note = new Note();
        note.setId(1L);
        note.setTitle("Day");
        note.setContent("A lot of work");
        when(noteRepository.save(note)).thenReturn(note);
        // Вызов
        Note note1 = noteService.createNote(note);
        // Проверка
        verify(noteRepository).save(note);
        assertEquals(note1, note);
    }

    @Test
    @DisplayName("noteFindAllTest")
    public void noteFindAllTest() {
        // Предпосылки
        NoteRepository noteRepository = mock(NoteRepository.class);
        NoteService noteService = new NoteService(noteRepository);

        Note note = new Note();
        note.setId(1L);
        note.setTitle("Day");
        note.setContent("A lot of work");
        List<Note> expectNotes = Collections.singletonList(note);
        when(noteRepository.findAll()).thenReturn(expectNotes);
        // Вызов
        List<Note> actualNotes = noteService.findAll();
        // Проверка
        assertEquals(expectNotes, actualNotes);
    }

    @Test
    @DisplayName("noteFindByIdTest")
    public void noteFindByIdTest() {
        // Предпосылки
        NoteRepository noteRepository = mock(NoteRepository.class);
        NoteService noteService = new NoteService(noteRepository);

        Note note = new Note();
        note.setId(1L);
        note.setTitle("Day");
        note.setContent("A lot of work");
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));
        //Вызов
        Optional<Note> note1 = noteService.findById(1L);
        // Проверка
        verify(noteRepository).findById(1L);
        assertNotNull(note1);
        assertEquals(Optional.of(note), note1);
    }
}
