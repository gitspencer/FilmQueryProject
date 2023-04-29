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
//		app.test();
		app.launch();
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
		int selection;
		menu();
		do {
			selection = input.nextInt();
			input.nextLine();
			switch(selection) {
			case 1:
				System.out.print("Enter film id: ");
				selection = input.nextInt();
				input.nextLine();
				Film film = db.findFilmById(selection);
				if (film != null) {
					System.out.println(film.getTitle() + ", Released: " + film.getReleaseYear() 
					+ ", Rating: " + film.getRating() + ", \n" + film.getDesc() + "\n");
				} else {
					System.out.println("No film found for search. \n");
				}
//				STILL THROWS ERRORS IF INPUT IS INVALID CHARACTERS OR NUMBER > INT RANGE
				menu();
				break;
			case 2:
//				UPDATE This for keyword search
				System.out.print("Enter film keyword: ");
				selection = input.nextInt();
				input.nextLine();
				System.out.println(db.findActorById(selection) + "\n");
				db.findActorById(selection);
				menu();
				break;
			case 3:
				System.out.println("Goodbye!");
				break;
			default:
				System.out.println("Invalid selection. \n");
				menu();
				break;
			}
		} while (selection != 3);

	}

	private void menu() {
		System.out.println("1) Look up film by film id");
		System.out.println("2) Look up film by keyword");
		System.out.println("3) Exit");
	}
	
}
