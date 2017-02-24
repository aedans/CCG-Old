#version 150 core

in vec3 position;
in vec2 textureCoords;

out vec2 passTextureCoords;

uniform mat4 transformationMatrix;

void main(void){
    gl_Position = transformationMatrix * vec4(position,1.0);
	passTextureCoords = textureCoords;
}