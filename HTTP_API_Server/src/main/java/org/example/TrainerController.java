package org.example;

import io.javalin.http.Context;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TrainerController {

    private ConcurrentHashMap<Integer, Trainer> trainers = new ConcurrentHashMap<Integer, Trainer>();
    private int lastId = 0;

    public TrainerController() {
        trainers.put(++lastId, new Trainer("Demont", "Kilian", new Date(102, Calendar.FEBRUARY, 11),
                new Equipe("My Favorites Pokemon",
                        List.of(new Pokemon(150, "Mewtwo", 122.0, 2.0, Type.PSYCHIC),
                                new Pokemon(131, "Lokhlass", 220.0, 2.5, Type.WATER, Type.ICE)))));
        trainers.put(++lastId, new Trainer("Holzer", "Julien", new Date(101, Calendar.MARCH, 11),
                new Equipe("Bests Pokemon !!!!",
                        List.of(new Pokemon(150, "Mewtwo", 122.0, 2.0, Type.PSYCHIC),
                                new Pokemon(131, "Lokhlass", 220.0, 2.5, Type.WATER, Type.ICE)))));

    }

    public void getOne(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        ctx.json(trainers.get(id));
    }

    public void getAll(Context ctx) {
        ctx.json(trainers);
    }

    public void create(Context ctx) {
        Trainer user = ctx.bodyAsClass(Trainer.class);
        trainers.put(++lastId, user);
        ctx.status(201);
    }

    public void delete(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        trainers.remove(id);
        ctx.status(204);
    }

    public void update(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Trainer user = ctx.bodyAsClass(Trainer.class);
        trainers.put(id, user);
        ctx.status(200);
    }
}
