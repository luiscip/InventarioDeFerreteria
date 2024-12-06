
# **Inventario de Ferretería**

Este proyecto es un sistema de gestión de inventario para una ferretería. Permite gestionar productos, categorías, marcas, ubicaciones, proveedores, ventas y compras. Desarrollado en **Java** usando **NetBeans**, con una interfaz gráfica en **JFrame** y una base de datos en **MySQL**.

---

## **Características**
- Gestión de usuarios con roles.
- Control de categorías, marcas y productos.
- Seguimiento de movimientos de stock.
- Registro de compras y ventas.
- Conexión con una base de datos MySQL.
- Interfaz gráfica amigable y funcional.

---

## **Requisitos previos**
Antes de ejecutar este proyecto, asegúrate de tener lo siguiente instalado:
1. [Java JDK 22](https://www.oracle.com/java/technologies/javase-downloads.html).
2. [NetBeans IDE 23 a ser posible](https://netbeans.apache.org/download/index.html).
3. [XAMPP](https://www.apachefriends.org/index.html) (para MySQL y Apache).
4. [MySQL Workbench ultima versión](https://dev.mysql.com/downloads/workbench/) (opcional, para gestionar la base de datos).

---

## **Configuración y Ejecución**

### **1. Configura el servidor local**
1. Abre **XAMPP Control Panel**.
2. Activa los módulos **Apache** y **MySQL**.

### **2. Configura la base de datos**
1. Accede a [phpMyAdmin](http://localhost/phpmyadmin).
2. Crea una nueva base de datos llamada `dbinventario`.
3. Importa el archivo SQL del esquema de la base de datos:
   - Ve a la pestaña **Importar**.
   - Selecciona el archivo `database/dbinventario.sql` incluido en este proyecto.
   - Haz clic en **Continuar** para importar el esquema y las tablas.

### **3. Configura el proyecto en NetBeans**
1. Clona este repositorio en tu máquina local:
   ```bash
   git clone https://github.com/luiscip/InventarioDeFerreteria.git
   ```
2. Abre el proyecto en **NetBeans**.
3. Asegúrate de que el archivo de conexión a la base de datos (`ConexionDB.java`) tenga los siguientes parámetros configurados:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/dbinventario";
   private static final String USER = "root";
   private static final String PASSWORD = ""; // Contraseña por defecto de XAMPP
   ```
4. Verifica que las bibliotecas de MySQL JDBC estén configuradas:
   - Descarga el conector [MySQL JDBC Driver](https://dev.mysql.com/downloads/connector/j/).
   - Agrega el archivo `.jar` al proyecto:
     - Haz clic derecho en el proyecto > **Propiedades** > **Bibliotecas** > **Añadir JAR/Carpeta**.

### **4. Ejecuta el proyecto**
1. En NetBeans, haz clic derecho en el proyecto y selecciona **Run**.
2. La aplicación abrirá la interfaz gráfica de usuario.

---

## **Estructura del Proyecto**
```plaintext
InventarioDeFerreteria/
│
├── database/
│   └── dbinventario.sql          # Archivo SQL con el esquema de la base de datos
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ConexionDB.java    # Archivo para gestionar la conexión a la base de datos
│   │   └── gui/
│   │       └── JFrameVentana.java # Ventana principal del sistema
│
├── README.md                     # Documentación del proyecto
```

---

## **Notas importantes**
1. La base de datos debe estar activa siempre que se ejecute la aplicación.
2. El archivo `dbinventario.sql` incluye la estructura de las tablas, relaciones y restricciones necesarias para que el sistema funcione correctamente.
3. Puedes personalizar las configuraciones de conexión según tu entorno de desarrollo.

---

## **Contribuciones**
Agradecimientos especiales a [Felipao99](https://github.com/Felipao99) por su colaboración en este proyecto.

Si deseas contribuir, sigue estos pasos:
1. Realiza un *fork* del repositorio.
2. Crea una nueva rama:
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```
3. Haz tus cambios y súbelos:
   ```bash
   git add .
   git commit -m "Añadida nueva funcionalidad"
   git push origin feature/nueva-funcionalidad
   ```
4. Abre un *pull request* y describe tus cambios.

---

## **Licencia**
Este proyecto está licenciado bajo los términos de [MIT License](LICENSE). Puedes usarlo, modificarlo y compartirlo bajo las condiciones de esta licencia.
