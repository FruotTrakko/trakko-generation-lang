add logger
add logging for each step
-> espacially file creation and path resolving

add user hint to issue reporting on error

add DaoGenerator functionality

add fileHeader template replacement

add Modules selection by reflection and class loader search
add type-atlas parsing
add type-atlas aggregation in type-atlases and map them to their lang

read resource
        // try (InputStream headerFileInputStream = ClassLoader.getSystemResourceAsStream(headerFilePath)) {
        //     Files.copy(headerFileInputStream, modelFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        // } catch (Exception ex) {
        //     throw new IOException(ex);
        // }