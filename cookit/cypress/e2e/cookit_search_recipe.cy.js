describe('template spec', () => {

  /* ==== Test Created with Cypress Studio ==== */
  it('SearchRecipe1', function() {
    /* ==== Generated with Cypress Studio ==== */
    cy.visit('http://localhost:8083/');
    cy.get('.v3ti-new-tag').click();
    cy.get(':nth-child(1) > .v3ti-context-item--label').click();
    cy.get('.v3ti-new-tag').click();
    cy.get(':nth-child(2) > .v3ti-context-item--label').click();
    cy.get('.btn').click();
    cy.get('tbody > tr > :nth-child(1)').click();
    cy.get('tbody > tr > :nth-child(1)').should('have.text', 'Teste Receita 1');
    /* ==== End Cypress Studio ==== */
  });
})