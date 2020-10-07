<template>
    <v-dialog v-model="stateDialog" max-width="400" persistent scrollable>
        <v-card :loading="savingUser" :disabled="savingUser">
            <v-card-title>
                {{action=='add'?'Nuevo Usuario':"Edición de Usuario"}}
            </v-card-title>
            <v-card-text>
                <br>
                <v-form ref="newUser" lazy-validation>
                    <v-text-field 
                    v-model="userForm.usuario" 
                    :rules="userForm.usuarioRules"
                    label="Usuario"
                    outlined
                    rounded
                    >

                    </v-text-field>
                    <v-text-field 
                    v-model="userForm.nombre" 
                    :rules="userForm.nombreRules"
                    label="Nombre"
                    outlined
                    rounded
                    >

                    </v-text-field>
                    <v-text-field 
                    v-model="userForm.numeroEmpleado" 
                    :rules="userForm.numeroEmpleadoRules"
                    label="Número de empleado"
                    outlined
                    rounded
                    type="number"
                    >

                    </v-text-field>
                    <v-select 
                    outlined
                    rounded
                    :items="userForm.puestoItems" 
                    label="Puestos" 
                    v-model="userForm.puesto" :rules="userForm.puestoRules">
                    </v-select>
                    <v-select
                    :items="userForm.departamentoEmpresaItems"
                    outlined
                    rounded
                    label="Departamentos"
                    v-model="userForm.departamentoEmpresa"
                    :rules="userForm.departamentoEmpresaRules"
                    >
                    </v-select>
                    <v-select
                    outlined
                    rounded
                    :items="hardwareItems"
                    item-text="descripcion"
                    item-value="idHardware"
                    v-model="userForm.detalle"
                    label="Hardware"
                    multiple
                    chips
                    deletable-chips
                    return-object
                    >
                    </v-select>
                    <v-switch label="Usuario activo/no activo" v-model="userForm.activo"></v-switch>
                </v-form>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn text @click="$emit('onclose')">Cancelar</v-btn>
                <v-btn text color="primary" @click="saveUpdateUser(action)">Guardar</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>
<script>
export default {
    props:[
        'opened',
        'hardwareItems',
        'action',
        'usuario',
    ],
    watch:{
        opened:function(val){
            if(val){
                if(this.action=="edit"){
                    this.userForm.usuario=this.usuario.usuario;
                    this.userForm.nombre=this.usuario.nombre;
                    this.userForm.numeroEmpleado=this.usuario.numeroDeEmpleado;
                    this.userForm.puesto=this.usuario.puesto;
                    this.userForm.departamentoEmpresa=this.usuario.departamentoEmpresa;
                    this.userForm.activo=this.usuario.activo;
                    this.userForm.detalle=this.usuario.detalle;
                }

                this.stateDialog=val;
            }else{
                this.$refs.newUser.reset();
                this.stateDialog=val;
            }
        }
    },
    methods:{
        async saveUpdateUser(action){
            if(this.$refs.newUser.validate()){
                let newUserFormat={
                    usuario:this.userForm.usuario,
                    nombre:this.userForm.nombre,
                    numeroDeEmpleado:this.userForm.numeroEmpleado,
                    puesto:this.userForm.puesto,
                    departamentoEmpresa:this.userForm.departamentoEmpresa,
                    activo:this.userForm.activo?1:0,
                    detalle:this.userForm.detalle
                };

                this.savingUser=true;

                if(action=='add'){
                    await this.$axios.post(this.$webserviceBaseURL+"/Users/Create",
                        newUserFormat
                    ).then((response)=>{
                        if(response.data.code==23){
                            this.$emit('onsuccess')
                            this.$refs.newUser.reset();
                        }

                        this.$emit('onclose',response.data.code,response.data.message);
                    }).catch((error)=>{
                        console.log(error);
                    });

                }else{
                    newUserFormat.idUsuario=this.usuario.idUsuario;

                    await this.$axios.put(this.$webserviceBaseURL+"/Users/Edit",
                        newUserFormat
                    ).then((response)=>{
                        if(response.data.code==24){
                            this.$emit('onsuccess')
                            this.$refs.newUser.reset();
                        }

                        this.$emit('onclose',response.data.code,response.data.message);
                    }).catch((error)=>{
                        console.log(error);
                    });
                }

                this.savingUser=false;
            }
        },
        showNotification(color,text){
            this.notificationSnackbar.state=false;
            this.notificationSnackbar.color=color;
            this.notificationSnackbar.text=text;
            
            this.notificationSnackbar.state=true;
        }
    },
    data:()=>({
        savingUser:false,
        stateDialog:false,
        userForm:{
            usuario: "",
            usuarioRules: [
                v => !!v || 'Usuario es requerido.',
                v => ( v &&v.length <= 30) || 'Usuario debe ser de menos de 30 caracteres.',
                v=>!/^\s+$/.test(v)||'Usuario no debe ser un espacio en blanco.'
            ],
            nombre: "",
            nombreRules: [
                v => !!v || 'Nombre es requerido.',
                v => (v&&v.length <= 100) || 'Nombre debe ser de menos de 100 caracteres',
                v=>!/^\s+$/.test(v)||'Nombre no debe ser un espacio en blanco'
            ],
            numeroEmpleado: "",
            numeroEmpleadoRules: [
                v => !!v || 'Numero de empleado es requerido',
                v => (v&&v.length <= 7) || 'Numero de empleado debe ser de menos de 7 caracteres.',
                v=>!/^\s+$/.test(v)||'Número de empleado no debe ser un espacio en blanco.',
                v=>/^\d+$/.test(v)||'Número de empleado debe ser unicamente un numero.'
            ],
            puesto: "",
            puestoItems:[
                "Programador Junior",
                "Programador Senior",
                "Programador Móvil",
                "Operativo 1 RRHH",
                "Administrador 1"
            ],
            puestoRules: [
                v => !!v || 'Puesto es requerido',
            ],
            departamentoEmpresa: "",
            departamentoEmpresaItems:[
                "Departamento de IT",
                "Departamento de RRHH",
                "Departamento de Admon"
            ],
            departamentoEmpresaRules: [
                v => !!v || 'Departamento es requerido',
            ],
            activo: true,
            activoRules: [
                v => !!v || 'Activo es requerido',
            ],
            detalle:[]
        }
    }),
}
</script>
<style scoped>

</style>