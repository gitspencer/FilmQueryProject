package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;
import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	private DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.test();
//    app.launch();
	}

	private void test() {
		Film film = db.findFilmById(0);
		if (film != null) {
			System.out.println(film);
		} else {
			System.out.println("No film found for search.");
		}

		Actor actor = db.findActorById(0);
		if (actor != null) {
			System.out.println(actor);
		} else {
			System.out.println("No actor found for search.");
		}
		
//		Update the list errors in case 0 or too large a number.
		List<Film> actorIdToFilm = db.findFilmsByActorId(0);
		if (actorIdToFilm != null) {
			System.out.println(actorIdToFilm);
		} else {
			System.out.println("No film found for search.");
		}
			
		List<Actor> filmIdToActor = db.findActorsByFilmId(2);
		if (filmIdToActor != null) {
			System.out.println(filmIdToActor);
		} else {
			System.out.println("No actor found for search.");
		}
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {

	}

}
