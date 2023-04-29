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

		List<Film> actorIdToFilm = db.findFilmsByActorId(0);
		if (!actorIdToFilm.isEmpty()) {
			System.out.println(actorIdToFilm);
		} else {
			System.out.println("No film found for search.");
		}

		List<Actor> filmIdToActor = db.findActorsByFilmId(0);
		if (!filmIdToActor.isEmpty()) {
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
		String keyword;
		menu();
		do {
			selection = input.nextInt();
			input.nextLine();
			switch(selection) {
			case 1:
				System.out.print("\nEnter film id: ");
				selection = input.nextInt();
				input.nextLine();
				Film film = db.findFilmById(selection);
				if (film != null) {
					System.out.println(film.getTitle() + ", Released: " + film.getReleaseYear() 
					+ ", Rating: " + film.getRating() + ", \n" + film.getDesc() + "\n");
				} else {
					System.out.println("No film found for search. \n");
				}
				menu();
				break;
			case 2:
				Scanner sc = new Scanner(System.in);
				System.out.print("\nEnter film keyword: ");
				keyword = sc.next();
				sc.nextLine();
				List<Film> word = db.findFilmByKeyword(keyword);
				if (!word.isEmpty()) {
					for (Film film2 : word) {
						System.out.println(film2.getTitle() + ", Released: " + film2.getReleaseYear() 
						+ ", Rating: " + film2.getRating() + ", \n" + film2.getDesc() + "\n");						
					}
				} else {
					System.out.println("No film found for search. \n");
				}
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
