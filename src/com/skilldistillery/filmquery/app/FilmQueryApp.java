package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;
import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Language;

public class FilmQueryApp {

	private DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

//	private void test() {
//		Film film = db.findFilmById(0);
//		if (film != null) {
//			System.out.println(film);
//		} else {
//			System.out.println("No film found for search.");
//		}
//
//		Actor actor = db.findActorById(0);
//		if (actor != null) {
//			System.out.println(actor);
//		} else {
//			System.out.println("No actor found for search.");
//		}
//
//		List<Film> actorIdToFilm = db.findFilmsByActorId(0);
//		if (!actorIdToFilm.isEmpty()) {
//			System.out.println(actorIdToFilm);
//		} else {
//			System.out.println("No film found for search.");
//		}
//
//		List<Actor> filmIdToActor = db.findActorsByFilmId(0);
//		if (!filmIdToActor.isEmpty()) {
//			System.out.println(filmIdToActor);
//		} else {
//			System.out.println("No actor found for search.");
//		}
//	}

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
				printFilmFromId(input);
				break;
			case 2:
				printFilmFromKeyword(input);
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
	
	private void printFilmFromId(Scanner input) {
		System.out.print("\nEnter film id: ");
		int idSelection = input.nextInt();
		input.nextLine();
		Film film = db.findFilmById(idSelection);
		Language language = db.findLanguage(idSelection);
		if (film != null) {
			System.out.print(film.getTitle() + ", Released: " + film.getReleaseYear() 
			+ ", Rating: " + film.getRating() + ", Language: " + language.getName() + "\nActors: "); 
			List<Actor> actors = film.getActors();
			for (Actor actor : actors) {
				if (actors.indexOf(actor) < actors.size() -1) {
					System.out.print(actor + ", ");
				} else {
					System.out.print(actor);							
				}
			}
			System.out.println("\n" + film.getDesc());
			System.out.println();
		} else {
			System.out.println("No film found for search. \n");
		}
		menu();
	}

	private void printFilmFromKeyword(Scanner input) {		
		System.out.print("\nEnter film keyword: ");
		String keyword = input.next();
		input.nextLine();				
		List<Film> filmWordSearch = db.findFilmByKeyword(keyword);
		if (!filmWordSearch.isEmpty()) {
			for (Film movie : filmWordSearch) {
				int id = movie.getFilmId();
				Language lang = db.findLanguage(id);
				System.out.print(movie.getTitle() + ", Released: " + movie.getReleaseYear() 
				+ ", Rating: " + movie.getRating() + ", Language: " + lang.getName() + "\nActors: ");
				List<Actor> actors = db.findActorsByFilmId(id);
				for (Actor actor : actors) {
					if (actors.indexOf(actor) < actors.size() -1) {
						System.out.print(actor + ", ");
					} else {
						System.out.print(actor);								
					}
				}
				System.out.println("\n" + movie.getDesc());
				System.out.println();
			}
		} else {
			System.out.println("No film found for search. \n");
		}
		menu();
	}
	
}
