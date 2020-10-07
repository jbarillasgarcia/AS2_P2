<template>
    <v-app-bar app>
        <v-app-bar-nav-icon v-if="!$vuetify.breakpoint.mdAndUp" @click="$emit('onopendrawer')"></v-app-bar-nav-icon>
        <v-toolbar-title>{{$router.currentRoute.name}}</v-toolbar-title>
        <v-progress-linear
        v-if="!isLoaded"
        indeterminate
        absolute
        bottom
        color="primary"
      ></v-progress-linear>
    </v-app-bar>  
</template>
<script>
export default {
    props:{
        isLoaded:Boolean
    }
}
</script>
<style scoped>

</style>