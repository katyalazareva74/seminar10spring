package ru.example.sem6homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.example.sem6homework.model.Note;
import ru.example.sem6homework.repository.NoteRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceAnnotationTest {
    /**
     * Модульное тестирование с аннотациями
     */
    @Mock
    private NoteRepository noteRepository;
    @InjectMocks
    private NoteService noteService;

    @Test
    @DisplayName("noteDeleteTest")
    public void noteDeleteTest() {
        // Предпосылки
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Day");
        note.setContent("A lot of work");
        // Вызов
        noteService.deleteNote(1L);
        // Проверка
        verify(noteRepository).deleteById(1L);
    }

    @Test
    @DisplayName("noteFindByIdTest")
    public void noteFindByIdTest() {
        // Предпосылки

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
