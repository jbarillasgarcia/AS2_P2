<template>
    <div>
        <AppBar :isLoaded="isLoaded" @onopendrawer="$emit('onopendrawer')"/>
        <v-row v-if="isLoaded">
            <v-col v-for="hardwareItem in hardwareData" :key="hardwareItem.idHardware" cols="12" xs="12" sm="6" md="4" lg="4" xl="3">
                <v-card>
                    <v-card-title>
                        <v-btn icon style="position:absolute;right:0px;top:0px;"><v-icon>clear</v-icon></v-btn>
                        {{hardwareItem.descripcion}}
                    </v-card-title>
                    <v-card-subtitle>{{hardwareItem.marca}} - No. serie {{hardwareItem.numeroDeSerie}}</v-card-subtitle>
                    <v-card-text>
                        <v-row class="pa-0 ma-0">
                            <v-col class="pa-0">
                                Stock:
                            </v-col>
                            <v-col class="pa-0">
                                <v-chip>{{hardwareItem.cantidadPiezas}}</v-chip>
                            </v-col>
                        </v-row>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </div>
</template>
<script>
import AppBar from '../GlobalComponents/AppBar'

export default {
    components:{
        AppBar
    },
    data:()=>({
        hardwareData:[],
        isLoaded:false
    }),
    methods:{
        async loadAllData(){
            await this.$axios.get(this.$webserviceBaseURL+"/Hardware").then((response)=>{
                if(response.data.code==11){
                    this.hardwareData=response.data.data;
                }
            }).catch((error)=>{
                console.log(error);
            });

            this.isLoaded=true;
        }
    },
    created(){
        this.loadAllData();
    }
}
</script>
<style scoped>

</style>