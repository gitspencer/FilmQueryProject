package com.skilldistillery.filmquery.database;

import java.sql.SQLException;
import java.util.List;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
	Film findFilmById(int fId);
	Actor findActorById(int aId);
	List<Actor> findActorsByFilmId(int filmId);
	List<Film> findFilmsByActorId(int actorId);
}