package io.lcydev.spawnercleaner.handlers;

public class ChunkWrapper {

    private String worldName;

    private final int x;

    private final int z;

    private final int[] cords;

    private ChunkState state = ChunkState.UNPROCESSED;

}
