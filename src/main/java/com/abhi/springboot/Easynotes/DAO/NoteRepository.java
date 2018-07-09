package com.abhi.springboot.Easynotes.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhi.springboot.Easynotes.Model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{

}
