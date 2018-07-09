package com.abhi.springboot.Easynotes.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.springboot.Easynotes.DAO.NoteRepository;
import com.abhi.springboot.Easynotes.Exception.ResourceNotFoundException;
import com.abhi.springboot.Easynotes.Model.Note;

@RestController
@RequestMapping("/api")
public class NotesController {

	@Autowired
	NoteRepository noteRepository;
	//GET ALL Notes
	@GetMapping("/notes")
	public List<Note> getAllNotes(){
		return noteRepository.findAll();
	}
	//Create New notes
	@PostMapping("/notes")
	public Note createNote(@Valid @RequestBody Note note) {
		return noteRepository.save(note);
	}
	
	//Get Single Note
	@GetMapping("/notes/{id}")
	public Note getNotesById(@PathVariable(value="id")Long noteId) {
		return noteRepository.findById(noteId).orElseThrow(()-> new ResourceNotFoundException("Notes", "Id", noteId));
	}
	//Update a note
	@PutMapping("/notes/{id}")
	public Note updateNotes(@PathVariable(value="id")Long noteId,@Valid@RequestBody Note noteDetails) {
		Note note = noteRepository.findById(noteId).orElseThrow(()->new ResourceNotFoundException("Notes", "ID", noteId));
		note.setTitle(noteDetails.getTitle());
		note.setContent(noteDetails.getContent());
		Note updateNote = noteRepository.save(note);
		return updateNote;
	}
	//Delete a note
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> delteNotes(@PathVariable(value="id")Long noteId){
		Note note = noteRepository.findById(noteId).orElseThrow(()->new ResourceNotFoundException("Notes", "ID", noteId));
		noteRepository.delete(note);
		return ResponseEntity.ok().build();
	}
	
}
