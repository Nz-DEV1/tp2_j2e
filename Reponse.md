# TP02 – Questions de compréhension

 **Matricule : 24171**

1. Différence entre IoC et DI

IoC (Inversion of Control) signifie que Spring prend le contrôle de la création et de la gestion des objets au lieu de laisser le programmeur tout faire avec `new`.

DI (Dependency Injection) est une manière de réaliser cette inversion. Spring fournit automatiquement à une classe les objets dont elle a besoin.

Par exemple, au lieu de créer un objet avec `new CricketCoach()`, on laisse Spring créer le bean et l'injecter dans le contrôleur.

 2. Pourquoi l'injection par constructeur est-elle recommandée ?

L'injection par constructeur est recommandée pour plusieurs raisons :

- **Immuabilité :** les dépendances peuvent être déclarées `final` et ne changent plus après la création de l'objet.
- **Testabilité :** il est plus facile de fournir des objets différents pendant les tests.
- **Sécurité :** une classe ne peut pas être créée sans recevoir les dépendances nécessaires.
- **Clarté :** on voit directement les dépendances dont la classe a besoin.

 3. Dans quelle situation obtient-on NoUniqueBeanDefinitionException ?

Cette erreur apparaît lorsque Spring trouve plusieurs beans du même type et ne sait pas lequel choisir pour l'injection.

Par exemple, si plusieurs classes implémentent l'interface `Coach`, Spring peut trouver `CricketCoach`, `BaseballCoach`, `TennisCoach` et `TrackCoach`.

On peut résoudre ce problème de deux façons :

- Utiliser `@Primary` pour indiquer le bean à choisir par défaut.
- Utiliser `@Qualifier` pour préciser exactement le bean souhaité.

Si les deux sont présents, `@Qualifier` est prioritaire sur `@Primary`.

 4. Comment Spring calcule-t-il l'id par défaut d'un bean ?

Par défaut, Spring utilise le nom de la classe en commençant par une minuscule.

Par exemple, la classe `CricketCoach` devient le bean `cricketCoach`.

On peut choisir un autre nom manuellement avec une annotation :

```java
@Component("myCoach")
```

Avec `@Bean`, on peut aussi donner un nom :

```java
@Bean("aquatic")
public Coach swimCoach() {
    return new SwimCoach();
}
```

 5. Quels sont les avantages et les inconvénients de @Lazy ?

L'initialisation paresseuse permet de créer un bean seulement lorsqu'il est utilisé.

    Avantages :

- Le démarrage de l'application peut être plus rapide.
- Les beans qui ne sont jamais utilisés ne sont pas créés.
- Cela peut réduire l'utilisation de la mémoire au démarrage.

**Inconvénients :**

- Les erreurs liées à certains beans peuvent apparaître seulement au moment de leur utilisation.
- La première utilisation peut être plus lente.

Il faut donc utiliser `@Lazy` avec parcimonie, car il n'est pas toujours nécessaire de retarder la création des beans.

 6. Pourquoi /check renvoie true puis false ?

Au début, le résultat était `true` parce que Spring utilise le scope singleton par défaut. Les deux injections de `CricketCoach` pointaient vers le même objet.

Ensuite, j'ai ajouté :

```java
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
```

Le résultat est devenu `false`, car Spring crée un nouvel objet à chaque demande d'un bean prototype.

Cela montre la différence entre les deux scopes :

- Singleton : une seule instance partagée.
- Prototype : une nouvelle instance à chaque demande.

 7. Quelles sont les étapes du cycle de vie d'un bean singleton ?

Les principales étapes sont :

1. Spring crée l'objet en appelant son constructeur.
2. Spring injecte les dépendances nécessaires.
3. La méthode annotée avec `@PostConstruct` est exécutée.
4. Le bean est prêt à être utilisé par l'application.
5. Lorsque l'application s'arrête, Spring appelle la méthode annotée avec `@PreDestroy`.
6. Le bean est ensuite détruit.

`@PreDestroy` n'est pas appelé automatiquement pour un bean prototype, car Spring ne gère pas sa destruction après l'avoir fourni à l'application. C'est au programmeur de gérer son nettoyage si nécessaire.

 8. Dans quels cas préfère-t-on @Bean plutôt que @Component ?

On préfère utiliser `@Bean` dans une classe `@Configuration` dans plusieurs situations :

1. Lorsqu'on utilise une classe externe ou une bibliothèque qu'on ne peut pas modifier pour lui ajouter `@Component`.
2. Lorsqu'on veut contrôler précisément la création d'un objet.
3. Lorsqu'on veut configurer un objet avec des paramètres ou lui donner un nom personnalisé.

Dans notre exercice, nous avons utilisé `@Bean("aquatic")` pour créer le bean `SwimCoach` sans ajouter `@Component` à sa classe.

---

 Tableau des scopes Spring

| Scope       | Description                                                                                    |
| ----------- | ---------------------------------------------------------------------------------------------- |
| Singleton   | Une seule instance du bean est créée pour tout le conteneur Spring. C'est le scope par défaut. |
| Prototype   | Une nouvelle instance est créée à chaque demande du bean.                                      |
| Request     | Une instance est créée pour chaque requête HTTP.                                               |
| Session     | Une instance est créée pour chaque session HTTP.                                               |
| Application | Une instance est créée pour toute l'application web.                                           |
| WebSocket   | Une instance est créée pour chaque session WebSocket.                                          |

**Conclusion :**

Ce TP m'a permis de mieux comprendre le fonctionnement de Spring, notamment la création des beans, l'injection des dépendances et leur cycle de vie. J'ai également appris à utiliser les différents scopes et à configurer des beans avec les annotations Spring.
