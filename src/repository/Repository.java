package repository;

import java.io.File;

/* Repository for reading and writing file*/

// T: model for writing data
// C: return type for reading data
public interface Repository<T, C> {
    final String path = new File("src").getAbsolutePath();
    
    C readFile();
    
    void writeFile(C entities);
}
