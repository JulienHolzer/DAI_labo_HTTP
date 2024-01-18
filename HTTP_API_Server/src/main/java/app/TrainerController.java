package app;

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
                        List.of(new Pokemon(150, "Mewtwo", 122.0, 2.0, Type.PSYCHIC),
                                new Pokemon(131, "Lokhlass", 220.0, 2.5, Type.WATER, Type.ICE))));
        trainers.put(++lastId, new Trainer("Holzer", "Julien", new Date(101, Calendar.MARCH, 11),
                        List.of(new Pokemon(150, "Mewtwo", 122.0, 2.0, Type.PSYCHIC),
                                new Pokemon(131, "Lokhlass", 220.0, 2.5, Type.WATER, Type.ICE))));

    }

    public void getOne(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        ctx.json(trainers.get(id));
    }

    public void getAll(Context ctx) {
        System.out.println("Hello");
        ctx.json(trainers);
    }

    public void create(Context ctx) {
        Trainer trainer = ctx.bodyAsClass(Trainer.class);
        trainers.put(++lastId, trainer);
        ctx.status(201);
    }

    public void delete(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        trainers.remove(id);
        ctx.status(204);
    }

    public void update(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Trainer trainer = ctx.bodyAsClass(Trainer.class);
        trainers.put(id, trainer);
        ctx.status(200);
    }
}
