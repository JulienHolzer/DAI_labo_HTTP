package org.example;

import io.javalin.Javalin;

/*
 * @author Kilian Demont
 */
public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7002);

        TrainerController trainerController = new TrainerController();
        app.get("/api/trainers", trainerController::getAll);
        app.get("/api/trainers/{id}", trainerController::getOne);
        app.post("/api/trainers/", trainerController::create);
        app.put("/api/trainers/{id}", trainerController::update);
        app.delete("/api/trainers/{id}", trainerController::delete);
    }
}