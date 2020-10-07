<template>
    <div>
        <AppBar :isLoaded="loaded" @onopendrawer="$emit('onopendrawer')"/>
        <NewEditUserDialog :action="action" :usuario="selectedUsuario" :opened="newUserDialog" :hardwareItems="hardwareItems" @onclose="onclose" @onsuccess="loadAllData()"/>
        <v-row v-if="loaded">
            <v-col v-for="user in usersData" :key="user.idUsuario" cols="12" xs="12" sm="6" md="4" lg="4" xl="3">
                <v-card>
                    <v-card-title>
                        <v-btn icon style="position: absolute; right:0px; top:0px;" @click="deleteUser(user.idUsuario)"><v-icon>clear</v-icon></v-btn>
                        {{user.nombre}}
                    </v-card-title>
                    <v-card-subtitle>{{user.puesto}} - {{user.departamentoEmpresa}}</v-card-subtitle>
                    <v-card-text>
                        <v-row class="pa-0 ma-0">
                            <v-col align-self="center"># hardware asignado: </v-col>
                            <v-col justify="end"><v-chip>{{user.detalle.length}}</v-chip></v-col>
                        </v-row>
                    </v-card-text>
                    <v-card-actions>
                        <v-btn rounded text outlined small color="primary" @click="onEdit(user)">Ver Usuario</v-btn>
                    </v-card-actions>
                </v-card>
            </v-col>
            <v-btn fab fixed right bottom color="primary" @click="action='add';newUserDialog=true;">
                <v-icon>
                    add
                </v-icon>
            </v-btn>
        </v-row>
        <v-snackbar 
            v-model="notificationSnackbar.state"
            :color="notificationSnackbar.color"
            :timeout="notificationSnackbar.timeout"
            >
            {{notificationSnackbar.text}}
        </v-snackbar>
    </div>
</template>
<script>
import AppBar from '../GlobalComponents/AppBar'
import NewEditUserDialog from './NewEditUserDialog'

export default {
    components:{
        AppBar,
        NewEditUserDialog,
    },
    data:()=>({
        usersData:[],
        hardwareItems:[],
        loaded:false,
        newUserDialog:false,
        action:"",
        selectedUsuario:"",
        notificationSnackbar:{
            state:false,
            text:"",
            color:"",
            timeout:3000
        }
    }),
    methods:{
        onEdit(usuario){
            this.action="edit";
            this.newUserDialog=true;
            this.selectedUsuario=usuario
        },
        onclose(responseCode,message){
            switch(responseCode){
                case 23:
                    this.showNotification("green",message);
                break;
                case 24:
                    this.showNotification("green",message);
                break;
                case 28:
                    this.showNotification("red",message);
                break;
                case 29:
                    this.showNotification("red",message);
                break;
                case 30:
                    this.showNotification("red",message);
                break;
            }

            this.newUserDialog=false;
        },
        loadUsers(){
            return this.$axios.get(this.$webserviceBaseURL+"/Users");
        },
        loadHardware(){
            return this.$axios.get(this.$webserviceBaseURL+"/Hardware");
        },
        async loadAllData(){
            await this.$axios.all([this.loadUsers(),this.loadHardware()]).then((responses)=>{
                if(responses[0].data.code==21){
                    this.usersData=responses[0].data.data;
                    if(responses[1].data.code==11){
                        this.hardwareItems=responses[1].data.data;
                    }
                }
            }).catch((errors)=>{
                console.log(errors);
            });

            this.loaded=true;
        },
        async deleteUser(id){
            await this.$axios.delete(this.$webserviceBaseURL+`/Users/Delete/${id}`).then((response)=>{
                if(response.data.code==22){
                    this.loadAllData();

                    this.showNotification("green",response.data.message);
                }else{
                    this.showNotification("red",response.data.message);
                }
            }).catch((error)=>{
                console.log(error);
            });
        },
        showNotification(color,text){
            this.notificationSnackbar.state=false;
            this.notificationSnackbar.color=color;
            this.notificationSnackbar.text=text;
            
            this.notificationSnackbar.state=true;
        }
    },
    created(){
        this.loadAllData();
    }
}
</script>
<style scoped>

</style>