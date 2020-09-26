<template>
    <v-app>
        <v-navigation-drawer 
            app
            :permanent="$vuetify.breakpoint.mdAndUp"
            floating
            color="primary"
            dark
            fixed
            v-model="open"
        >
            <v-list v-for="(item,index) in drawerItems" :key="index">
                <v-list-item link :to="item.path">
                    <v-list-item-content>
                        <v-list-item-title>
                            {{item.text}}
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>
        <v-main>
            <v-container fluid>
                <router-view @onopendrawer="openCloseDrawer"/>
            </v-container>
        </v-main>
    </v-app>
</template>
<script>

export default {
    components:{

    },
    methods:{
        openCloseDrawer(){
            if(this.open){
                this.open=!this.open;
            }else{
                this.open=!this.open;
            }
        }
    },
    data:()=>({
        open:false,
        drawerItems:[
            {
                path:"/",
                text:"Inicio"
            },
            {
                path:"/Users",
                text:"Usuarios"
            },
            {
                path:"/Hardware",
                text:"Hardware"
            }
        ]
    })
}
</script>
<style scoped>

</style>