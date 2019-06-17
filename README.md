# Fragments SlideButton

[Project created with Android Studio V3.4.1]


## Project Base.
Creación de un Botón Deslizante con esquinas redondeadas para mostrar Fragmentos:
Un ImageView se comportará como el botón deslizante; éste está dentro de un RelativeLayout, que será el área donde se podrá desplazar.
De acuerdo a la posición en la que se encuentre el botón, se mostrará su fragmento.
El botón tiene dos animaciones para mover el botón a la derecha o izquierda, según sea el caso:
\n[moveButtonLeft]: 
\n-Si el botón está a la izquierda entonces: Si el botón no llega a sobrepasar el 50% del área de desplazamiento, se regresa a su posición origen.
\n-Si el botón está a la derecha entonces: Si la posición del botón es mayor a su tamaño, entonces el botón se desplaza a la izquierda.
\n[moveButtonRigth]:
\n-Si el botón está a la izquierda entonces: Si el botón sobrepasa el 50% del área de desplazamiento, el botón se desplaza a su derecha.
\n-Si el botón está a la derecha entonces: Si la posición del botón es menor a su tamaño, se regresa a su posición origen.