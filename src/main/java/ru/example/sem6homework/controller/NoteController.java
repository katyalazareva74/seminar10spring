package ru.example.sem6homework.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.sem6homework.exception.ResourceNotFoundException;
import ru.example.sem6homework.model.Note;
import ru.example.sem6homework.service.NoteService;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    private final NoteService service;

    /**
     * Запрос возвращает все записи
     * @return - возвращает список записей
     */
    @GetMapping
    public List<Note> findAllNotes(){
        return  service.findAll();
    }

    /**
     * Запрос создает запись
     * @param note - сама запись
     * @return - возращает сделанную запись
     */
    @PostMapping
    public Note addNote(@RequestBody Note note){
        return service.createNote(note);
    }

    /**
     * Запрос на поиск записи по id
     * @param id - номер записи
     * @return - возвращает найденную запись по номеру, а если запись не найдена, то
     * выбрасывается исключение ResourceNotFoundException
     * @throws ResourceNotFoundException - это исключение выдает код ошибки 404 (ресурс не найден) на стороне клиента
     */
    @GetMapping("{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") Long id) throws ResourceNotFoundException {
           return ResponseEntity.ok(service.findById(id).orElseThrow(()-> new ResourceNotFoundException("Note not found with id " + id)));
    }

    /**
     * Запрос на редактирование записи по номеру (id)
     * @param id - номер записи
     * @param note - новая запись
     * @return - возвращает отредактированную запись, а если запись не найдена, то
     * выбрасывается исключение ResourceNotFoundException
     * @throws ResourceNotFoundException - это исключение выдает иод ошибку 404 (ресурс не найден) на стороне клиента
     */
    @PutMapping("{id}")
    public ResponseEntity<Note> updateNote(@PathVariable("id") Long id, @RequestBody Note note) throws ResourceNotFoundException {
        note.setId(id);
        ResponseEntity<Note> note1 = ResponseEntity.ok(service.findById(id).orElseThrow(()-> new ResourceNotFoundException("Note not found with id " + id)));
        service.createNote(note);
        return ResponseEntity.ok(note);
    }

    /**
     * Запрос на удаление записи по id
     * @param id - номер записи
     * @return - если запись найдена, то возвращается код 200, а если запись не найдена, то
     * выбрасывается исключение ResourceNotFoundException
     * @throws ResourceNotFoundException - это исключение выдает код ошибки 404 (ресурс не найден) на стороне клиента
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        ResponseEntity<Note> note = ResponseEntity.ok(service.findById(id).orElseThrow(()-> new ResourceNotFoundException("Note not found with id " + id)));
        service.deleteNote(id);
        return ResponseEntity.ok().build();
    }
}
